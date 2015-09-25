package ru.abigovor.store;


import ru.abigovor.models.Client;

import java.util.Collection;

public interface Storage {

    public Collection<Client> values();

    public int add(Client client);

    public void edit(Client client);

    public void delete(int id);

    public Client get(int id);

    public Collection<Client> findByName(String name);

    public int generateId();

    public void close();
}
