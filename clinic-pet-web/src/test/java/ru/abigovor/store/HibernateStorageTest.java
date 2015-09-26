package ru.abigovor.store;

import org.junit.Test;
import ru.abigovor.models.Client;

import static org.junit.Assert.assertEquals;

public class HibernateStorageTest {

    @Test
    public void test_add() throws Exception {
        final HibernateStorage storage = new HibernateStorage();
        final Client addClient = new Client(-1, "hibernate", "test", "qwe", 'f', null);
        addClient.setRole(1);
        int id = storage.add(addClient);
        Client client = storage.get(id);
        assertEquals(id, client.getId());
        storage.delete(id);
        storage.close();
    }
}