package ru.abigovor.servlets;

import main.ru.abigovor.Clinic;
import ru.abigovor.models.SingletonClinic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserViewServlet extends HttpServlet {

    private Clinic clinic = SingletonClinic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", clinic.getClients());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/UserView.jsp");
        dispatcher.forward(req, resp);
    }
}
