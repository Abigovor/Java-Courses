package ru.abigovor.store;


import ru.abigovor.models.Client;
import ru.abigovor.models.Role;

import java.util.Collection;
import java.util.List;

public interface Storage {

    public Collection<Client> values();

    public int add(Client client);

    public void edit(Client client);

    public void delete(int id);

    public Client get(int id);

    public Client findByEmail(String email);

    public Collection<Client> findByName(String name);

    public int generateId();

    public void close();

    List<Role> roles();
}
