package ru.abigovor.models;

import main.ru.abigovor.Pet;

public class Client {
    private final int id;
    private final String name;
    private String surname;
    private String password;
    private char sex;
    private final Pet pet;

    public String getName() {
        return name;
    }

    public Pet getPet() {
        return pet;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public char getSex() {
        return sex;
    }

    public Client(int id, String name, String surname, String password, char sex, Pet pet) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.sex = sex;
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", pet=" + pet +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (sex != client.sex) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (password != null ? !password.equals(client.password) : client.password != null) return false;
        if (pet != null ? !pet.equals(client.pet) : client.pet != null) return false;
        if (surname != null ? !surname.equals(client.surname) : client.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) sex;
        result = 31 * result + (pet != null ? pet.hashCode() : 0);
        return result;
    }
}
