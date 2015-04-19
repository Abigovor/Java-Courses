package lesson_6_homework;

/**
 * Created by Single on 18.04.2015.
 */
public class Dog implements Pet {

    private String name;


    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}