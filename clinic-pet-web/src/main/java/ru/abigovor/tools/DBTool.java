package ru.abigovor.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.abigovor.models.Role;
import ru.abigovor.store.Factory;
import ru.abigovor.store.Storages;

public final class DBTool {
    private static Factory factory;

    private DBTool() {
    }

    static {
        try {
            factory = new ClassPathXmlApplicationContext("spring-context.xml").getBean(Storages.class).getFactory();
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

        for (Role role1 : storages.hibernateFactory.getRoleDAO().values()) {
            System.out.println(role1.getName());
        }
    }
}
