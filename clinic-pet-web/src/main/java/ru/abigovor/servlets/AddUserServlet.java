package ru.abigovor.servlets;

import main.ru.abigovor.*;
import ru.abigovor.models.SingletonClinic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Single on 15.09.2015.
 */
public class AddUserServlet extends HttpServlet {
    Clinic clinic = SingletonClinic.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String petName = req.getParameter("petName");
        String pet = req.getParameter("1");

        if (null != pet)
            if (pet.equals("dog"))
                clinic.addClient(new Client(UniqueIdentifier.getNextInt(), clientName, new Dog(petName)));
            else
                clinic.addClient(new Client(UniqueIdentifier.getNextInt(), clientName, new Cat(petName)));

        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }
}
