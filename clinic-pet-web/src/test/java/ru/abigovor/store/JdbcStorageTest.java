package ru.abigovor.store;

import org.junit.*;
import org.junit.rules.ExpectedException;
import ru.abigovor.models.Client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JdbcStorageTest {

    private JdbcStorage storage;
    private Client client;

    @Before
    public void setUp() {
        storage = new JdbcStorage();
        client = new Client(-1, "test", "test_edit", "paswd", 'm', null);
    }

    @After
    public void clean() {
        storage.close();
        storage = null;
        client = null;
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Ignore
    @Test
    public void test_add() throws Exception {
        int id = storage.add(client);
        assertEquals(id, storage.get(id).getId());
        storage.delete(id);
        storage.close();
    }

    @Ignore
    @Test
    public void test_edit_user() throws Exception {
        int id = storage.add(client);
        final Client newUser = new Client(id, "new_test_name", "new_test_edit_name", "paswd", 'm', null);
        storage.edit(newUser);

        assertEquals(storage.get(id), newUser);
        storage.delete(id);
        storage.close();
    }


    @Ignore
    @Test
    public void test_delete() throws Exception {
        int id = storage.add(client);
        assertEquals(id, storage.get(id).getId());
        storage.delete(id);
        try {
            storage.get(id);
            fail("Exception expected");
        } catch (IllegalStateException e) {
            assertEquals(String.format("Client with id %s not found", id), e.getMessage());
        }
        storage.close();
    }

    @Ignore
    @Test
    public void test_delete_negative() throws Exception {
        int id = -5;
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(String.format("User %s does not exists", id));
        storage.delete(id);
        storage.close();
    }

    @Ignore
    @Test
    public void test_find_by_name() throws Exception {
        int id = storage.add(client);
        String name = client.getName().toUpperCase();
        assertEquals(id, storage.findByName(name).iterator().next().getId());
        storage.delete(id);
        storage.close();
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void test_find_by_name_negative() throws Exception {
        String name_dont_use_in_DB = "invented_name";
        storage.findByName(name_dont_use_in_DB);
        storage.close();
    }
}