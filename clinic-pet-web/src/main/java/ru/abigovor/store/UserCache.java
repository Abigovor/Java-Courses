package ru.abigovor.store;


import ru.abigovor.models.Client;

import java.util.Collection;

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
    }
}