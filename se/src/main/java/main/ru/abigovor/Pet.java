package main.ru.abigovor;

/**
 * Created by Single on 18.04.2015.
 */
public interface Pet {

    /**
     * Получить имя питомца
     */
    public String getName();

    public String getClazz();

    public Pet getNewPet(String name);

    public String toString();
}