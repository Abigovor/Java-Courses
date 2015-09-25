package ru.abigovor.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemoryStorageTest {

    private MemoryStorage storage;

    @Before
    public void setUp() {
        this.storage = new MemoryStorage();
    }

    @After
    public void clean() {
        this.storage = null;
    }

    @Test
    public void test_add() throws Exception {

    }
}