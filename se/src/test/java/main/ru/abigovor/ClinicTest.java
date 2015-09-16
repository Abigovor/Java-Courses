package main.ru.abigovor;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import main.ru.abigovor.UserException.UserException;
import main.ru.abigovor.UserException.UserPetException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicTest {

    private Clinic clinic;

    /**
     * Всё, что было веведенно на экран, получаем при помощи output.toString()
     */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Выполняется перд каждым тестом.
     */
    @Before
    public void init() {
        this.clinic = new Clinic();
        // Подменяем консольный вывод.
        System.setOut(new PrintStream(output));
    }

    /**
     * Выполняется после каждого теста.
     */
    @After
    public void clear() {
        this.clinic = null;
        System.setOut(null);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_add_client() throws Exception {
        Client client = new Client(1, "Test1", new Dog("dogName1"));
        clinic.addClient(client);
        assertEquals(1, clinic.size());
    }

    @Test
    public void test_find_client_By_Name() throws Exception {
        Client client = new Client(2, "Test2", new Dog("dogName2"));
        clinic.addClient(client);
        assertEquals(clinic.findClientByName("Test2").get(0), client);
    }

    @Test
    public void test_find_list_of_clients_by_name() throws Exception {
        Client client1 = new Client(1, "Test3", new Dog("dogName2"));
        Client client2 = new Client(2, "Test3", new Dog("dogName2"));
        Client client3 = new Client(3, "no legal", new Dog("dogName2"));

        clinic.addClient(client1);
        clinic.addClient(client2);
        clinic.addClient(client3);

        List<Client> listOfClients = Arrays.asList(client1, client2);

        assertArrayEquals(clinic.findClientByName("Test3").toArray(), listOfClients.toArray());
    }

    @Test(expected = UserException.class)
    public void test_find_client_by_name_negative() throws Exception {
        Client client = new Client(4, "Test4", new Dog("dogName2"));
        clinic.addClient(client);
        assertEquals(clinic.findClientByName("Test1").get(0), client);
    }

    @Test(expected = UserException.class)
    public void test_find_client_by_name_list_of_clients_is_empty() throws Exception {
        clinic.findClientByName("clientName");
    }

    @Test
    public void test_find_client_by_pet_name() throws Exception {
        Client client1 = new Client(1, "Client1", new Cat("catName1"));
        Client client2 = new Client(2, "Client2", new Cat("catName2"));
        Client client3 = new Client(3, "Client3", new Cat("catName3"));

        clinic.addClient(client1);
        clinic.addClient(client2);
        clinic.addClient(client3);

        assertEquals(clinic.findClientByPetName("catName2").get(0), client2);
    }

    @Test
    public void test_find_list_of_clients_by_pet_name() throws Exception {
        Client client1 = new Client(1, "Client1", new Cat("Abi"));
        Client client2 = new Client(2, "Client2", new Cat("Abi"));
        Client client3 = new Client(3, "Client3", new Cat("catName"));

        clinic.addClient(client1);
        clinic.addClient(client2);
        clinic.addClient(client3);

        Client[] answer = {client1, client2};

        assertArrayEquals(clinic.findClientByPetName("Abi").toArray(), answer);
    }

    @Test(expected = UserException.class)
    public void test_find_client_by_pet_name_negative() throws Exception {
        Client client = new Client(1, "clientName", new Dog("dogName"));
        clinic.addClient(client);
        assertEquals(clinic.findClientByPetName("noName").get(0), client);
    }

    @Test(expected = UserException.class)
    public void test_find_client_by_Pet_Name_list_of_clients_is_empty() throws Exception {
        clinic.findClientByPetName("petName");
    }

    @Test
    public void test_rename_client() throws Exception {
        int id = 1;
        String newClientName = "newClientName";
        Client client = new Client(id, "ClientName", new Cat("catName"));

        clinic.addClient(client);
        clinic.renameClient(id, newClientName);

        assertEquals(clinic.getClientById(id).getName(), newClientName);
    }

    @Test
    public void test_rename_client_when_list_of_clients_is_empty() {
        int id = 1;
        String newClientName = "newClientName";
        try {
            clinic.renameClient(id, newClientName);
            fail("Exception expected");
        } catch (UserException e) {
            assertEquals("Client list is empty!", e.getMessage());
        }
    }

    @Test
    public void test_rename_client_client_not_found() {
        int id = 1;
        String newClientName = "newClientName";
        Client client = new Client(id, "ClientName", new Cat("catName"));

        clinic.addClient(client);
        try {
            clinic.renameClient(++id, newClientName);
            fail("Exception expected");
        } catch (UserException e) {
            assertEquals("The client not found!", e.getMessage());
        }
    }

    @Test
    public void test_rename_pet() throws Exception {
        int id = 1;
        String newPetName = "newPetName";
        Client client = new Client(id, "ClientName", new Cat("catName"));

        clinic.addClient(client);
        clinic.renamePet(id, newPetName);

        assertEquals(clinic.getClientById(id).getPet().getName(), newPetName);
    }

    @Test(expected = UserPetException.class)
    public void test_rename_null_pet() throws Exception {
        int id = 1;
        String newPetName = "newPetName";
        Client client = new Client(id, "ClientName", null);

        clinic.addClient(client);
        clinic.renamePet(id, newPetName);
    }

    @Test
    public void test_rename_pet_when_list_of_clients_is_empty() {
        int id = 1;
        String newPetName = "newPetName";
        try {
            clinic.renamePet(id, newPetName);
            fail("Exception expected");
        } catch (UserException e) {
            assertEquals("Client list is empty!", e.getMessage());
        } catch (UserPetException e) {
            /* NOP */
        }
    }

    @Test
    public void test_remove_client() throws Exception {
        int id = 1;
        Client client = new Client(id, "clientName", new Dog("dogName"));
        clinic.addClient(client);
        clinic.removeClient(id);
        assertEquals(0, clinic.size());
    }

    @Test
    public void test_remove_client_when_list_of_clients_is_empty() {
        int id = 2;
        try {
            clinic.removeClient(id);
            fail("Exception expected");
        } catch (UserException e) {
            assertEquals("Client list is empty!", e.getMessage());
        }

    }

    @Test(expected = UserException.class)
    public void test_remove_client_negative() throws Exception {
        int id = 1;
        Client client = new Client(id, "clientName", new Dog("dogName"));
        clinic.addClient(client);
        clinic.removeClient(id + 1);
    }

    @Test
    public void test_remove_pet() throws Exception {
        int id = 2;
        Dog dog = new Dog("dogName");
        clinic.addClient(new Client(id, "clientName", dog));
        clinic.removePet(id);
        assertNull(clinic.getClientById(id).getPet());
    }

    @Test
    public void test_remove_pet_when_list_of_clients_is_empty() throws UserException, UserPetException {
        expectedException.expect(UserException.class);
        expectedException.expectMessage("Client list is empty!");
        int id = 2;
        clinic.removePet(id);
    }

    @Test
    public void test_remove_pet_when_pet_is_null() throws UserException, UserPetException {
        expectedException.expect(UserPetException.class);
        expectedException.expectMessage("Pet not find!");
        int id = 2;
        Pet pet = null;
        clinic.addClient(new Client(id, "clientName", pet));
        clinic.removePet(id);
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, clinic.size());
    }

    @Test(expected = UserException.class)
    public void test_get_client_by_id_negative() throws Exception {
        int id = 1;
        Client client = new Client(id, "clientName", new Dog("dogName"));
        clinic.addClient(client);

        clinic.getClientById(5);
    }
}