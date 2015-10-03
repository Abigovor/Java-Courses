package ru.abigovor.servlets;

import ru.abigovor.tools.DBTool;
import ru.abigovor.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем параметр заголовка id
        int id = req.getIntHeader("id");
        try {
            DBTool.getFactory().getUserDAO().delete(Integer.valueOf(req.getParameter("id")));
        } catch (IllegalStateException e) {
            req.setAttribute("message", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/Home.jsp");
            dispatcher.forward(req, resp);
        }
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.getFactory().close();
    }
}
