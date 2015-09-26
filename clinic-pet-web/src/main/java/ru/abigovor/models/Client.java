package ru.abigovor.models;

import main.ru.abigovor.Pet;

public class Client extends Base {
    private int id;
    private String name;
    private String surname;
    private String password;
    private int role;
    private char sex;
    private Pet pet;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Client() {
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
