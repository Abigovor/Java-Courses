package ru.abigovor.servlets;

import ru.abigovor.tools.DBTool;
import ru.abigovor.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchString = req.getParameter("search");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/UserView.jsp");
        try {
            req.setAttribute("clients", DBTool.getFactory().getUserDAO().findByName(searchString));
            dispatcher.forward(req, resp);
        } catch (IllegalStateException e) {
            dispatcher = req.getRequestDispatcher("/views/user/Home.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.getFactory().close();
    }
}
