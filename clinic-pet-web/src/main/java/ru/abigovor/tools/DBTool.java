package ru.abigovor.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.abigovor.models.Client;
import ru.abigovor.models.Role;
import ru.abigovor.store.Factory;
import ru.abigovor.store.Storages;

public final class DBTool {
    private static Factory factory;

    private DBTool() {
    }

    static {
        try {
            //factory = new ClassPathXmlApplicationContext("spring-context.xml").getBean(Storages.class).getFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Factory getFactory() {
        return factory;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storages storages = context.getBean(Storages.class);

        Client addClient = new Client();
        addClient.setName("name");
        addClient.setSurname("surname");
        addClient.setPassword("pass");
        addClient.setSex('m');
        addClient.setEmail("email@email.org");

        Role role = new Role();
        role.setId(1);
        addClient.setRole(role);

        int userID = storages.getFactory().getUserDAO().add(addClient);

        addClient.setId(userID);
        addClient.setName("NEW_NAME");
        addClient.setSurname("NEW_Surname");
        storages.getFactory().getUserDAO().edit(addClient);

//        Client client = storages.getFactory().getUserDAO().findByEmail(addClient.getEmail());
//        System.out.println(client);

        for (Client findClient : storages.getFactory().getUserDAO().values()) {
            System.out.println(findClient);
        }

    }
}
