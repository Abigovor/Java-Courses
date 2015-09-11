package ru.lessons.lesson_6_homework;

/**
 * Created by Single on 18.04.2015.
 */
public class Cat implements Pet {

    private String name;

    /**
     * Конструктор
     *
     * @param name Имя животного
     */
    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Cat getNewPet(String name) {
        return new Cat(name);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;

        Cat cat = (Cat) o;

        if (name != null ? !name.equals(cat.name) : cat.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}