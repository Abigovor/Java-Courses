package ru.abigovor.servlets;

import ru.abigovor.store.UserCache;

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

    private final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchString = req.getParameter("search");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/UserView.jsp");
        try {
            req.setAttribute("clients", USER_CACHE.findByName(searchString));
            dispatcher.forward(req, resp);
        } catch (IllegalStateException e) {
            dispatcher = req.getRequestDispatcher("/views/user/index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        USER_CACHE.close();
    }
}
