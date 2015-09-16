package main.ru.abigovor;

/**
 * Created by Single on 18.04.2015.
 */
interface Pet {

    /**
     * Получить имя питомца
     */
    String getName();

    public String getClazz();

    Pet getNewPet(String name);

    String toString();
}