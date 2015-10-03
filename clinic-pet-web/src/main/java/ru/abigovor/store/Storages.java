package ru.abigovor.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abigovor.store.implementations.hibernameIml.HibernateFactory;

@Service
public class Storages {
    @Autowired
    public HibernateFactory hibernateFactory;

    public Factory getFactory() {
        return hibernateFactory;
    }
}
