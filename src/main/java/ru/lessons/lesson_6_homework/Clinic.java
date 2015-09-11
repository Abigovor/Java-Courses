package ru.lessons.lesson_6_homework;

import ru.lessons.lesson_6_homework.UserException.UserException;
import ru.lessons.lesson_6_homework.UserException.UserPetException;

import java.util.*;

/**
 * Класс описывает клинику
 * Created by Single on 18.04.2015.
 */
public class Clinic {
    private static final String PET_EXP = "Pet not find!";
    private static final String USER_EXP = "The client not found!";
    private static final String LIST_IS_EMPTY = "Client list is empty!";
    /**
     * Список клиентов
     */
    private final Map<Integer, Client> clients = new HashMap<>();


    public Clinic() {
    }

    /**
     * Добавление клиента
     *
     * @param client Клиент
     */
    public void addClient(final Client client) {
        clients.put(client.getId(), client);
    }


    /**
     * Поиск клиента по имени
     *
     * @param name Имя клиента
     * @return Список найденных клиентов
     */
    public List<Client> findClientByName(final String name) throws UserException {
        List<Client> findClients = new ArrayList<>();
        if (!this.clients.isEmpty()) {
            for (Map.Entry<Integer, Client> entry : clients.entrySet()) {
                if (entry.getValue().getName().equals(name)) {
                    findClients.add(entry.getValue());
                }
            }
        } else
            throw new UserException(LIST_IS_EMPTY);

        listIsEmpty(findClients);
        return findClients;
    }

    /**
     * Поиск клиента по кличке питомца
     *
     * @param petName Кличка питомца
     * @return Клиент
     */
    public List<Client> findClientByPetName(final String petName) throws UserException {
        List<Client> findClients = null;
        if (!this.clients.isEmpty()) {
            findClients = new ArrayList<>();
            for (Map.Entry<Integer, Client> pair : this.clients.entrySet()) {
                if (pair.getValue().getPet().getName().equals(petName)) {
                    findClients.add(pair.getValue());
                }
            }
        } else
            throw new UserException(LIST_IS_EMPTY);

        listIsEmpty(findClients);
        return findClients;
    }

    /**
     * проверяем пуст ли найденный список клиентов, если пуст выбрасываем исклчение
     *
     * @param clients список найденных клиентов
     * @throws ru.lessons.lesson_6_homework.UserException.UserException
     */
    private void listIsEmpty(List<Client> clients) throws UserException {
        if (clients.isEmpty())
            throw new UserException(USER_EXP);
    }

    /**
     * Редактируем имя клиента
     *
     * @param id      id пользовотеля
     * @param newName Новое имя
     */
    public void renameClient(int id, String newName) throws UserException {
        Client client = null;
        if (!clients.isEmpty()) {
            client = clients.get(id);
            if (null != client) {
                clients.put(id, new Client(id, newName, client.getPet()));
            } else
                throw new UserException(USER_EXP);
        } else
            throw new UserException(LIST_IS_EMPTY);
    }


    /**
     * Редактируем кличку питомца
     *
     * @param id         id клиента
     * @param newPetName Новая кличка питомца
     */

    public void renamePet(int id, String newPetName) throws UserException, UserPetException {
        Client client = null;
        if (!clients.isEmpty()) {
            client = getClientById(id);
            if (null != client.getPet()) {
                clients.put(id, new Client(id, client.getName(), client.getPet().getNewPet(newPetName)));
            } else throw new UserPetException(PET_EXP);
        } else throw new UserException(LIST_IS_EMPTY);
    }

    public Client getClientById(int id) throws UserException {
        Client client = clients.get(id);
        if (null == client)
            throw new UserException(USER_EXP);

        return client;
    }


    /**
     * Удаление клиента
     *
     * @param id уникальный идентификатор клиента
     * @throws ru.lessons.lesson_6_homework.UserException.UserException Если клиента нет, выкидывается исключение
     */

    public void removeClient(int id) throws UserException {
        if (!clients.isEmpty()) {
            Iterator<Map.Entry<Integer, Client>> iterator = clients.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Client> pair = iterator.next();
                if (pair.getKey() == id)
                    iterator.remove();
                else throw new UserException(USER_EXP);
            }
        } else throw new UserException(LIST_IS_EMPTY);
    }

    /**
     * Удаление питомца у клиента
     *
     * @param id уникальный идентификатор клиента
     * @throws ru.lessons.lesson_6_homework.UserException.UserPetException Если питомца нет, выбрасываем исключение
     */
    public void removePet(int id) throws UserException, UserPetException {
        if (!clients.isEmpty()) {
            Client temp = clients.get(id);
            if (null != temp.getPet()) {
                clients.put(id, new Client(id, temp.getName(), null));
            } else throw new UserPetException(PET_EXP);
        } else throw new UserException(LIST_IS_EMPTY);
    }

    /**
     * Показываем всех клиентов
     */
    public void showClient() throws UserException {
        if (!clients.isEmpty())
            for (Map.Entry<Integer, Client> entry : clients.entrySet())
                System.out.println(entry.getKey() + " " + entry.getValue());
        else throw new UserException(LIST_IS_EMPTY);
    }


    public int size() {
        return clients.size();
    }
}
