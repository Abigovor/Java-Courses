package ru.abigovor.store;

import ru.abigovor.store.implementations.hibernameIml.HibernateFactory;

public final class Storages {
    private static HibernateFactory hibernateFactory;

    private Storages() {
    }

    public static Factory getHibernateFactory() {
        if (null == hibernateFactory)
            hibernateFactory = new HibernateFactory();
        return hibernateFactory;
    }
}
