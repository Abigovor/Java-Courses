package ru.abigovor.servlets;

import ru.abigovor.models.Client;
import ru.abigovor.tools.DBTool;
import ru.abigovor.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Client user = DBTool.getFactory().getUserDAO().findByEmail(login);
        if (null != user) {
            if (user.getPassword().equals(password)) {
                req.setAttribute("user", user);
                dispatcher = req.getRequestDispatcher("/views/user/Home.jsp");
            } else {
                dispatcher = req.getRequestDispatcher("/index.jsp");
                req.setAttribute("message", "Неверный пароль!");
            }
        } else {
            dispatcher = req.getRequestDispatcher("/index.jsp");
            req.setAttribute("message", "Пользователя с таким логином не существует!");
        }
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.getFactory().close();
    }
}
