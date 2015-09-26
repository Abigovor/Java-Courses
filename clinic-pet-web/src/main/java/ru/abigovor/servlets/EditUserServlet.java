package ru.abigovor.servlets;

import main.ru.abigovor.Pet;
import ru.abigovor.models.Client;
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
public class EditUserServlet extends HttpServlet {

    private final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));

        try {
            req.setAttribute("user", USER_CACHE.get(id));
        } catch (IllegalStateException e) {
            req.setAttribute("message", e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/EditUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        String clientName = req.getParameter("clientName");
        String clientSurname = req.getParameter("clientSurname");
        String userSex = req.getParameter("sex");

        try {
            Pet pet = USER_CACHE.get(id).getPet();
            Client client = new Client(id, clientName, clientSurname, "pswd", userSex.charAt(0), pet);
            client.setRole(1);
            USER_CACHE.edit(client);
        } catch (IllegalStateException e) {
            req.setAttribute("message", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/index.jsp");
            dispatcher.forward(req, resp);
        }

        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }

    @Override
    public void destroy() {
        super.destroy();
        USER_CACHE.close();
    }
}

