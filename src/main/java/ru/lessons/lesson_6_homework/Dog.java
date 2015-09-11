package ru.lessons.lesson_6_homework;

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
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public Dog getNewPet(String name) {
        return new Dog(name);
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