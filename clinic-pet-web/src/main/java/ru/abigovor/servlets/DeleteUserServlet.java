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
public class DeleteUserServlet extends HttpServlet {

    private final static Clinic CLINIC = SingletonClinic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем параметр заголовка id
        int id = req.getIntHeader("id");
        try {
            CLINIC.removeClient(Integer.valueOf(req.getParameter("id")));
        } catch (UserException e) {
            System.out.println("sad");
            req.setAttribute("message", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/index.jsp");
            dispatcher.forward(req, resp);
        }
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }
}
