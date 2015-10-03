package ru.abigovor.tools;

import ru.abigovor.store.Factory;
import ru.abigovor.store.Storages;

public final class DBTool {
    private static Factory factory = Storages.getHibernateFactory();

    private DBTool() {
    }

    public static Factory getFactory() {
        return factory;
    }

    public static void main(String[] args) {
        System.out.println(DBTool.getFactory().getRoleDAO().values().iterator().next().getName());
        System.out.println(DBTool.getFactory().getUserDAO().values().iterator().next());
    }
}
