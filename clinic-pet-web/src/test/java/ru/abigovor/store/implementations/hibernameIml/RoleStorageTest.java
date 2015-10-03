package ru.abigovor.store.implementations.hibernameIml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.abigovor.models.Role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class RoleStorageTest {
    @Autowired
    private RoleStorage storage;
    private Role addRole;

    @Before
    public void setUp() throws Exception {
        addRole = new Role();
        String ROLE_NAME = "test";
        addRole.setName(ROLE_NAME);
        String ROLE_PERMISSION = "role permission";
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
            final String NEW_ROLE_NAME = "newRoleName";
            addRole.setName(NEW_ROLE_NAME);
            final String NEW_ROLE_PERMISSION = "newRolePermission";
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