package main.ru.abigovor;

/**
 * Created by Single on 18.04.2015.
 */
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

    public Client(int id, String name, Pet pet) {
        this.id = id;
        this.name = name;
        this.pet = pet;
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

        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (pet != null ? !pet.equals(client.pet) : client.pet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pet != null ? pet.hashCode() : 0);
        return result;
    }
}
