package ru.lessons.lesson_6_homework;

/**
 * Created by Single on 18.04.2015.
 */
interface Pet {

    /**
     * Получить имя питомца
     */
    String getName();

    Pet getNewPet(String name);

    String toString();
}