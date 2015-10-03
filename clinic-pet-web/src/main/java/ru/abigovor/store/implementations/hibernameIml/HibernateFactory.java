package ru.abigovor.store.implementations.hibernameIml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abigovor.store.dao.RoleDAO;
import ru.abigovor.store.dao.UserDAO;
import ru.abigovor.store.Factory;

@Service
public class HibernateFactory implements Factory {
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    @Autowired
    public HibernateFactory(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public RoleDAO getRoleDAO() {
        return roleDAO;
    }
}
