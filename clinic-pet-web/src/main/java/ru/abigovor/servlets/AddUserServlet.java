package ru.abigovor.servlets;

import main.ru.abigovor.*;
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
        String petName = req.getParameter("petName");
        String[] petType = req.getParameterValues("1");

        Pet pet = null;
        if (petType.length >= 0)
            pet = (petType[0].equals("dog")) ? new Dog(petName) : new Cat(petName);

        USER_CACHE.add(new Client(USER_CACHE.generateId(), clientName, pet));

        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }
}
