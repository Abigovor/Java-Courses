package ru.lessons.lesson_6_homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.lessons.lesson_6_homework.UserException.UserException;
import ru.lessons.lesson_6_homework.UserException.UserPetException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void test_find_client_by_pet_name() throws Exception {
        Client client = new Client(5, "Test5", new Cat("catName"));
        clinic.addClient(client);
        assertEquals(clinic.findClientByPetName("catName").get(0), client);
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

    @Test
    public void testRenameClient() throws Exception {
        int id = 1;
        String newClientName = "newClientName";
        Client client = new Client(id, "ClientName", new Cat("catName"));

        clinic.addClient(client);
        clinic.renameClient(id, newClientName);

        assertEquals(clinic.getClientById(id).getName(), newClientName);
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
    public void test_remove_client() throws Exception {
        int id = 1;
        Client client = new Client(id, "clientName", new Dog("dogName"));
        clinic.addClient(client);
        clinic.removeClient(id);
        assertEquals(0, clinic.size());
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