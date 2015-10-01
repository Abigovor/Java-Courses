package ru.abigovor.servlets;

import main.ru.abigovor.Pet;
import ru.abigovor.models.Client;
import ru.abigovor.models.Role;
import ru.abigovor.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        int role_id = Integer.valueOf(req.getParameter("role_id"));
        String clientName = req.getParameter("clientName");
        String clientSurname = req.getParameter("clientSurname");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String userSex = req.getParameter("sex");

        try {
            Pet pet = USER_CACHE.get(id).getPet();
            Client client = new Client(id, clientName, clientSurname, password, userSex.charAt(0), pet);
            client.setEmail(email);
            Role role = new Role();
            role.setId(role_id);
            client.setRole(role);

            USER_CACHE.edit(client);
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
        USER_CACHE.close();
    }
}

