package ru.abigovor.servlets;

import ru.abigovor.tools.DBTool;
import ru.abigovor.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserViewServlet", urlPatterns = "/user/view")
public class UserViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", DBTool.getFactory().getUserDAO().values());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/UserView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.getFactory().close();
    }
}
