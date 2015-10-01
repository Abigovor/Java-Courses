package ru.abigovor.store;

import org.junit.Test;
import ru.abigovor.models.Client;
import ru.abigovor.models.Role;

import static org.junit.Assert.assertEquals;

public class HibernateStorageTest {

    @Test
    public void test_add_user() throws Exception {
        final HibernateStorage storage = new HibernateStorage();
        final Client addClient = new Client();
        addClient.setName("test_add_user");
        addClient.setSurname("surname");
        addClient.setPassword("pass");
        addClient.setSex('m');
        addClient.setEmail("test_add_user@art.org");

        final Role role = new Role();
        role.setId(1);
        addClient.setRole(role);

        int id = storage.add(addClient);
        try {
            Client client_from_db = storage.get(id);
            assertEquals(addClient.getEmail(), client_from_db.getEmail());
        } finally {
            storage.delete(id);
            storage.close();
        }
    }

    @Test
    public void test_find_by_email() throws Exception {
        final HibernateStorage storage = new HibernateStorage();
        final String login = "test_find_by_email@tr.com";
        final Client addClient = new Client();
        addClient.setId(-1);
        addClient.setName("test_find_by_email");
        addClient.setSurname("surname");
        addClient.setPassword("pass");
        addClient.setSex('f');
        addClient.setEmail(login);
        final Role role = new Role();
        role.setId(1);      // Прежпологается, что в базе есть запись с id 1 в таблице role
        addClient.setRole(role);

        final int id = storage.add(addClient);

        try {
            assertEquals(addClient.getEmail(), storage.findByEmail(login).getEmail());
        } finally {
            storage.delete(id);
            storage.close();
        }
    }
}