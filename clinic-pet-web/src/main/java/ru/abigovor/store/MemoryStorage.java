package ru.abigovor.store;

import ru.abigovor.models.Client;
import ru.abigovor.store.DAO.Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class MemoryStorage implements Storage<Client> {

    private final AtomicInteger ids = new AtomicInteger();

    private final ConcurrentHashMap<Integer, Client> users = new ConcurrentHashMap<Integer, Client>();

    @Override
    public Collection<Client> values() {
        return this.users.values();
    }

    @Override
    public int add(Client client) {
        this.users.put(client.getId(), client);
        return client.getId();
    }

    @Override
    public void edit(Client client) {
        this.users.replace(client.getId(), client);
    }

    @Override
    public void delete(int id) throws IllegalStateException {
        if (null == this.users.remove(id))
            throw new IllegalStateException(String.format("Client with id %s not found", id));
    }

    public Collection<Client> findByName(String name) throws IllegalStateException {
        List<Client> foundUsers = new ArrayList<>();
        users.entrySet().stream()
                .filter(e -> e.getValue().getName().equalsIgnoreCase(name))
                .forEach(e -> foundUsers.add(e.getValue()));
        if (foundUsers.isEmpty())
            throw new IllegalStateException(String.format("Client %S not found", name));
        return foundUsers;
    }

    @Override
    public Client get(int id) throws IllegalStateException {
        Client client = this.users.get(id);
        if (null == this.users.get(id))
            throw new IllegalStateException(String.format("Client with id %s not found", id));
        return client;
    }

    @Override
    public int generateId() {
        return this.ids.incrementAndGet();
    }

    @Override
    public void close() {
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }
}
