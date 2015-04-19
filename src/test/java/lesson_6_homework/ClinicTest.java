package lesson_6_homework;

import lesson_6_homework.UserException.UserException;
import lesson_6_homework.UserException.UserPetException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClinicTest {

    private Clinic clinic;
    private Client client;

    /**
     * Всё, что было выведено на экран, можно получить при помощи output.toString().
     */
    protected final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * вызывается перед каждым методом
     */
    @Before
    public void init() {
        clinic = new Clinic(10);
        System.setOut(new PrintStream(output));
    }

    /**
     * вызывается после выполнения каждого теста
     */
    @After
    public void clear() {
        clinic = null;
        System.setOut(null);
    }

    @Test
    public void testAddClient() throws Exception {
        client = new Client("Bob", new Cat("Tom"));
        clinic.addClient(client);
        assertEquals(clinic.isEmpty(), false);
    }

    @Test
    public void testFindClientByName() throws Exception {
        client = new Client("Bob", new Cat("Tom"));
        clinic.addClient(client);
        assertEquals(clinic.findClientByName("Bob"), client);
    }

    @Test(expected = UserException.class)
    public void testFindClientByNameNegative() throws Exception {
        clinic.findClientByName("Bobs");
    }


    @Test
    public void testFindClientByPetName() throws Exception {
        client = new Client("Bob", new Cat("Tom"));
        clinic.addClient(client);
        assertEquals(clinic.findClientByPetName("Tom"), client);
    }

    @Test(expected = UserPetException.class)
    public void testFindClientByPetNameNegative() throws Exception {
        client = new Client("Bob", new Cat("Tom"));
        clinic.addClient(client);
        assertEquals(clinic.findClientByPetName("Cat"), client);
    }

    @Test
    public void testRenameClient() throws Exception {
        Client newClient = new Client("Jon", new Cat("Tom"));

        client = new Client("Bob", new Cat("Tom"));
        clinic.addClient(client);
        client = clinic.renameClient("Bob", "Jon");
        assertTrue(client.equals(newClient));
    }

    @Test
    public void testRenamePet() throws Exception {
        Client newClient = new Client("Jon", new Cat("Garfild"));

        client = new Client("Jon", new Cat("Tom"));
        clinic.addClient(client);
        client = clinic.renamePet("Tom", "Garfild");

        assertTrue(client.equals(newClient));
    }


    @Test
    public void testRemovePet() throws Exception {
        client = new Client("Bob", new Cat("Tom"));
        clinic.addClient(client);
        clinic.showClient();
    }

    @Test
    public void testShowClient() throws Exception {
        clinic.showClient();
        assertEquals("Client list is empty!", output.toString().trim());
    }
}