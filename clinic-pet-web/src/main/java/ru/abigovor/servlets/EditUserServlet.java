package ru.abigovor.servlets;

import main.ru.abigovor.Clinic;
import main.ru.abigovor.UserException.UserException;
import main.ru.abigovor.UserException.UserPetException;
import ru.abigovor.models.SingletonClinic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Single on 16.09.2015.
 */
public class EditUserServlet extends HttpServlet {

    private static final Clinic CLINIC = SingletonClinic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));

        try {
            req.setAttribute("user", CLINIC.getClientById(id));
        } catch (UserException e) {
            req.setAttribute("message", e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/EditUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        String newUserName = req.getParameter("clientName");
        String newPetName = req.getParameter("petName");

        try {
            CLINIC.renamePet(id, newPetName);
            CLINIC.renameClient(id, newUserName);
        } catch (UserException | UserPetException e) {
            req.setAttribute("message", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/index.jsp");
            dispatcher.forward(req, resp);
        }

        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }


}

