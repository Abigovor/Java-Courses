package ru.abigovor.store.implementations.hibernameIml;

import ru.abigovor.store.dao.RoleDAO;
import ru.abigovor.store.dao.UserDAO;
import ru.abigovor.store.Factory;

public class HibernateFactory implements Factory {
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    @Override
    public UserDAO getUserDAO() {
        if (null == userDAO)
            userDAO = new UserStorage();
        return userDAO;
    }

    @Override
    public RoleDAO getRoleDAO() {
        if (null == roleDAO)
            roleDAO = new RoleStorage();
        return roleDAO;
    }
}
