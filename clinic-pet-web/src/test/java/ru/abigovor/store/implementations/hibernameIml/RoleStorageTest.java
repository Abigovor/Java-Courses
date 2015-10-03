package ru.abigovor.store.implementations.hibernameIml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.abigovor.models.Role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleStorageTest {
    private RoleStorage storage;
    private Role addRole;

    private final String ROLE_NAME = "test";
    private final String ROLE_PERMISSION = "role permission";
    private final String NEW_ROLE_NAME = "newRoleName";
    private final String NEW_ROLE_PERMISSION = "newRolePermission";

    @Before
    public void setUp() throws Exception {
        storage = new RoleStorage();
        addRole = new Role();
        addRole.setName(ROLE_NAME);
        addRole.setPermission(ROLE_PERMISSION);
    }

    @After
    public void tearDown() throws Exception {
        storage = null;
        addRole = null;
    }

    @Test
    public void test_add_role() throws Exception {
        final int id = storage.add(addRole);
        try {
            Role getRole = storage.get(id);
            assertEquals(id, getRole.getId());
            assertEquals(addRole.getName(), getRole.getName());
            assertEquals(addRole.getPermission(), getRole.getPermission());
        } finally {
            storage.delete(id);
        }
    }

    @Test
    public void test_edit_role() throws Exception {
        final int id = storage.add(addRole);
        try {
            assertNotNull(storage.get(id));
            addRole.setName(NEW_ROLE_NAME);
            addRole.setPermission(NEW_ROLE_PERMISSION);
            storage.edit(addRole);
            final Role getRole = storage.get(id);
            assertEquals(NEW_ROLE_NAME, getRole.getName());
            assertEquals(NEW_ROLE_PERMISSION, getRole.getPermission());
        } finally {
            storage.delete(id);
        }
    }
}