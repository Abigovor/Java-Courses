package ru.abigovor.store;

import ru.abigovor.store.dao.RoleDAO;
import ru.abigovor.store.dao.UserDAO;

public interface Factory {

    UserDAO getUserDAO();

    RoleDAO getRoleDAO();
}
