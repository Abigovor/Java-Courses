package ru.abigovor.store.implementations.hibernameIml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.abigovor.models.Client;
import ru.abigovor.models.Role;
import ru.abigovor.store.Storages;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class UserStorageTest {

    @Autowired
    private Storages storage;

    private Client addClient;

    @Before
    public void setUp() throws Exception {
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

    private void cleanAndCloseTheConnection(int id) {
        storage.getFactory().getUserDAO().delete(id);
        storage.getFactory().getUserDAO().close();
    }

    @Test
    public void test_init() throws Exception {
        assertNotNull(storage);
    }

    @Test
    @Transactional(readOnly = false)
    public void test_add_user() throws Exception {
        addClient.setName("test_add_user");
        int id = storage.getFactory().getUserDAO().add(addClient);
        try {
            Client client_from_db = storage.getFactory().getUserDAO().get(id);
            assertEquals(addClient.getEmail(), client_from_db.getEmail());
        } finally {
            cleanAndCloseTheConnection(id);
        }
    }

    @Test
    @Transactional(readOnly = false)
    public void test_edit_user() throws Exception {
        final String newName = "newUserName";
        final String newSurname = "newSurname";
        final String newPassword = "newPassword";
        final int id = storage.getFactory().getUserDAO().add(addClient);
        try {
            assertEquals(id, storage.getFactory().getUserDAO().get(id).getId());
            addClient.setName(newName);
            addClient.setSurname(newSurname);
            addClient.setPassword(newPassword);
            storage.getFactory().getUserDAO().edit(addClient);
            final Client editClient = storage.getFactory().getUserDAO().get(id);
            assertEquals(addClient.getName(), editClient.getName());
            assertEquals(addClient.getSurname(), editClient.getSurname());
            assertEquals(addClient.getPassword(), editClient.getPassword());
        } finally {
            cleanAndCloseTheConnection(id);
        }
    }

    @Test
    @Transactional(readOnly = false)
    public void test_find_by_email() throws Exception {
        final String login = "test_find_by_email@tr.com";
        addClient.setName("test_find_by_email");
        addClient.setEmail(login);
        final int id = storage.getFactory().getUserDAO().add(addClient);
        try {
            assertEquals(addClient.getEmail(), storage.getFactory().getUserDAO().findByEmail(login).getEmail());
        } finally {
            cleanAndCloseTheConnection(id);
        }
    }


    @Test
    @Transactional(readOnly = false)
    public void test_find_by_name() throws Exception {
        final String findName = "TestFindByName";
        addClient.setName(findName);
        final int id = storage.getFactory().getUserDAO().add(addClient);
        try {
            Collection<Client> clients = storage.getFactory().getUserDAO().findByName(findName.toUpperCase());
            assertEquals(addClient.getId(), clients.iterator().next().getId());
        } finally {
            cleanAndCloseTheConnection(id);
        }
    }

    @Test
    @Transactional(readOnly = false)
    public void test_find_by_role_name() throws Exception {
        final String findRoleName = "ADMIN";
        final int id = storage.getFactory().getUserDAO().add(addClient);
        try {
            Collection<Client> clients = storage.getFactory().getUserDAO().findByRoleName(findRoleName);
            assertEquals(id, clients.iterator().next().getId());
        } finally {
            cleanAndCloseTheConnection(id);
        }
    }
}