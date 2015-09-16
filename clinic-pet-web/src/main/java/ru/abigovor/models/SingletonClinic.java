package ru.abigovor.models;

import main.ru.abigovor.Clinic;

/**
 * Created by Single on 16.09.2015.
 */
public class SingletonClinic {
    private static Clinic instance;

    public static Clinic getInstance() {
        if (instance == null)
            instance = new Clinic();
        return instance;
    }
}
