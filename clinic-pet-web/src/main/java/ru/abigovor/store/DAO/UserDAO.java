package ru.abigovor.store.dao;

import ru.abigovor.models.Client;

import java.util.Collection;

public interface UserDAO extends Storage<Client> {
    public Client findByEmail(String email);

    public Collection<Client> findByName(String name);

    public Collection<Client> findByRoleName(String role);
}
