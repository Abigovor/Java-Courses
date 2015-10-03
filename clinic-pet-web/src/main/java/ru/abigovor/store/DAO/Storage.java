package ru.abigovor.store.dao;


import java.util.Collection;

public interface Storage<T> {

    public Collection<T> values();

    public int add(T obj);

    public void edit(T obj);

    public void delete(int id);

    public T get(int id);

    public int generateId();

    public void close();
}
