package ru.abigovor.store;


import ru.abigovor.models.Client;
import ru.abigovor.models.Role;

import java.util.Collection;
import java.util.List;

public class UserCache implements Storage {

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
        return this.storage.get(id);
    }

    @Override
    public Collection<Client> findByName(String name) {
        return this.storage.findByName(name);
    }

    @Override
    public int generateId() {
        return this.storage.generateId();
    }

    @Override
    public void close() {
        this.storage.close();
    }

    @Override
    public Client findByEmail(String email) {
        return this.storage.findByEmail(email);
    }

    public List<Role> roles() {
        return this.storage.roles();
    }
}