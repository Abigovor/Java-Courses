package ru.abigovor.store.implementations.hibernameIml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.abigovor.models.Client;
import ru.abigovor.models.Role;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserStorageTest {
    private UserStorage storage;
    private Client addClient;

    @Before
    public void setUp() throws Exception {
        storage = new UserStorage();
        addClient = new Client();
        addClient.setName("name");
        addClient.setSurname("surname");
        addClient.setPassword("pass");
        addClient.setSex('m');
        addClient.setEmail("email@email.org");

        final Role role = new Role();
        role.setId(1);
        addClient.setRole(role);
    }

    @After
    public void tearDown() throws Exception {
        storage = null;
        addClient = null;
    }

    @Test
    public void test_add_user() throws Exception {
        addClient.setName("test_add_user");
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
    public void test_edit_user() throws Exception {
        final String newName = "newUserName";
        final String newSurname = "newSurname";
        final String newPassword = "newPassword";
        final int id = storage.add(addClient);
        try {
            assertEquals(id, storage.get(id).getId());
            addClient.setName(newName);
            addClient.setSurname(newSurname);
            addClient.setPassword(newPassword);
            storage.edit(addClient);
            final Client editClient = storage.get(id);
            assertEquals(addClient.getName(), editClient.getName());
            assertEquals(addClient.getSurname(), editClient.getSurname());
            assertEquals(addClient.getPassword(), editClient.getPassword());
        } finally {
            storage.delete(id);
            storage.close();
        }
    }

    @Test
    public void test_find_by_email() throws Exception {
        final String login = "test_find_by_email@tr.com";
        addClient.setName("test_find_by_email");
        addClient.setEmail(login);
        final int id = storage.add(addClient);
        try {
            assertEquals(addClient.getEmail(), storage.findByEmail(login).getEmail());
        } finally {
            storage.delete(id);
            storage.close();
        }
    }


    @Test
    public void test_find_by_name() throws Exception {
        final String findName = "TestFindByName";
        addClient.setName(findName);
        final int id = storage.add(addClient);
        try {
            List<Client> clients = storage.findByName(findName.toUpperCase());
            assertEquals(addClient.getId(), clients.get(0).getId());
        } finally {
            storage.delete(id);
            storage.close();
        }
    }

    @Test
    public void test_find_by_role_name() throws Exception {
        final String findRoleName = "admin";
        final int id = storage.add(addClient);
        try {
            List<Client> clients = storage.findByRoleName(findRoleName.toUpperCase());
            System.out.println(clients.get(0));
            System.out.println(id + " " + clients.get(0).getId());
            assertEquals(id, clients.get(0).getId());
        } finally {
            storage.delete(id);
            storage.close();
        }
    }
}