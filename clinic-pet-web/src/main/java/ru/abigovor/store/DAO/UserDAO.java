package ru.abigovor.store.DAO;

import ru.abigovor.models.Client;

import java.util.Collection;
import java.util.List;

public interface UserDAO extends Storage<Client> {
    public Client findByEmail(String email);

    public Collection<Client> findByName(String name);

    public List findByRoleName(String role);
}
