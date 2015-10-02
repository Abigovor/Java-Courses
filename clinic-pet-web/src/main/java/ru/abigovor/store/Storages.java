package ru.abigovor.store;

public class Storages {
    private static Storages instance;
    private UserStorage userStorage;
    private RoleStorage roleStorage;

    private Storages() {
    }

    public static Storages getInstance() {
        if (null == instance)
            instance = new Storages();
        return instance;
    }

    public UserStorage getUserStorage() {
        if (null == userStorage)
            this.userStorage = new UserStorage();
        return userStorage;
    }

    public RoleStorage getRoleStorage() {
        if (null == roleStorage)
            this.roleStorage = new RoleStorage();
        return roleStorage;
    }
}