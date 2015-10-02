package ru.abigovor.servlets;

import ru.abigovor.models.Client;
import ru.abigovor.models.Role;
import ru.abigovor.store.Storages;
import ru.abigovor.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Storages.getInstance().getRoleStorage().values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/AddClient.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String clientName = req.getParameter("clientName");
        final String clientSurname = req.getParameter("clientSurname");
        final String userSex = req.getParameter("sexH");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");
        final int role_id = Integer.valueOf(req.getParameter("role_id"));

/*
        String petName = req.getParameter("petName");
        String[] petType = req.getParameterValues("petType");

        Pet pet = null;
        if (petType.length >= 0 && !petName.isEmpty())
            pet = (petType[0].equals("dog")) ? new Dog(petName) : new Cat(petName);
*/

        final Client client = new Client();
        client.setId(Storages.getInstance().getUserStorage().generateId());
        client.setName(clientName);
        client.setSurname(clientSurname);
        client.setPassword(password);
        client.setSex(userSex.charAt(0));
        client.setEmail(email);
        final Role role = new Role();
        role.setId(role_id);
        client.setRole(role);

        Storages.getInstance().getUserStorage().add(client);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.getFactory().close();
    }
}
