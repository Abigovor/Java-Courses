package ru.abigovor.servlets;

import main.ru.abigovor.Clinic;
import main.ru.abigovor.UserException.UserException;
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
public class SearchUserServlet extends HttpServlet {

    private static final Clinic CLINIC = SingletonClinic.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchString = req.getParameter("search");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/UserView.jsp");
        try {
            req.setAttribute("clients", CLINIC.findClientByName(searchString));
            dispatcher.forward(req, resp);
        } catch (UserException e) {
            dispatcher = req.getRequestDispatcher("/views/user/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
