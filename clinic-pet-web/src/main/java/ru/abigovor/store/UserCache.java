package ru.abigovor.store;


import ru.abigovor.models.Client;
import ru.abigovor.models.Role;
import ru.abigovor.store.DAO.Storage;

import java.util.Collection;
import java.util.List;

public class UserCache implements Storage<Client> {

    private static final UserCache INSTANCE = new UserCache();

    private Storage storage = new HibernateStorage();

    public static UserCache getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<Client> values() {
        return this.storage.values();
    }

    @Override
    public int add(Client client) {
        return this.storage.add(client);
    }

    @Override
    public void edit(Client client) {
        this.storage.edit(client);
    }

    @Override
    public void delete(int id) {
        this.storage.delete(id);
    }

    @Override
    public Client get(int id) {
        return (Client) this.storage.get(id);
    }


    public Collection<Client> findByName(String name) {
        return null;
    }

    @Override
    public int generateId() {
        return this.storage.generateId();
    }

    @Override
    public void close() {
        this.storage.close();
    }

    public Client findByEmail(String email) {
        return null;
    }

    public List<Role> roles() {
        return null;
    }
}