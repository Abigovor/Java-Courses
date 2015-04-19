package lesson_6_homework;

/**
 * Created by Single on 18.04.2015.
 */
public class Client {
    private String name;
//    private final int id;
    private final Pet pet;

//    public int getId() {
//        return id;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Pet getPet() {
        return pet;
    }

    public Client(String name, Pet pet) {
        this.name = name;
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
