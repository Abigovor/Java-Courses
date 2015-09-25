package ru.abigovor.servlets;

import main.ru.abigovor.Cat;
import main.ru.abigovor.Dog;
import main.ru.abigovor.Pet;
import ru.abigovor.models.Client;
import ru.abigovor.store.UserCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Single on 15.09.2015.
 */
public class AddUserServlet extends HttpServlet {
    private final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String clientSurname = req.getParameter("clientSurname");
        String userSex = req.getParameter("sexH");

        String petName = req.getParameter("petName");
        String[] petType = req.getParameterValues("petType");

        Pet pet = null;
        if (petType.length >= 0 && !petName.isEmpty())
            pet = (petType[0].equals("dog")) ? new Dog(petName) : new Cat(petName);

        Client client = new Client(USER_CACHE.generateId(), clientName, clientSurname, "qwe", userSex.charAt(0), pet);
        System.out.println("ADD CLIENT: " + client);
        USER_CACHE.add(client);

        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }
}
