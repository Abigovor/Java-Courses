package ru.abigovor.store.command;

import org.hibernate.Session;

public interface Command<T> {
    T process(Session session);
}