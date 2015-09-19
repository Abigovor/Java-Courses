package ru.abigovor.store;


import main.ru.abigovor.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserCache {
    private static final UserCache INSTANCE = new UserCache();

    private final AtomicInteger ids = new AtomicInteger();

    private final ConcurrentHashMap<Integer, Client> users = new ConcurrentHashMap<Integer, Client>();

    public static UserCache getInstance() {
        return INSTANCE;
    }

    public Collection<Client> values() {
        return this.users.values();
    }

    public void add(Client client) {
        this.users.put(client.getId(), client);
    }

    public void edit(Client client) {
        this.users.replace(client.getId(), client);
    }

    public void delete(int id) throws IllegalStateException {
        if (null == this.users.remove(id))
            throw new IllegalStateException(String.format("Client with id %s not found", id));
    }

    public List<Client> findByName(String name) throws IllegalStateException {
        List<Client> foundUsers = new ArrayList<>();
        users.entrySet().stream()
                .filter(e -> e.getValue().getName().equalsIgnoreCase(name))
                .forEach(e -> foundUsers.add(e.getValue()));
        if (foundUsers.isEmpty())
            throw new IllegalStateException(String.format("Client %S not found", name));
        return foundUsers;
    }

    public Client get(int id) throws IllegalStateException {
        Client client = this.users.get(id);
        if (null == this.users.get(id))
            throw new IllegalStateException(String.format("Client with id %s not found", id));
        return client;
    }

    public int generateId() {
        return this.ids.incrementAndGet();
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }
}