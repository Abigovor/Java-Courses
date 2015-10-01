package ru.abigovor.models;

public class Dog implements Pet {

    private int id;
    private String name;


    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int setId(int id) {
        return this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String setName(String name) {
        return this.name = name;
    }

    @Override
    public String getClazz() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;

        Dog dog = (Dog) o;

        if (name != null ? !name.equals(dog.name) : dog.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}