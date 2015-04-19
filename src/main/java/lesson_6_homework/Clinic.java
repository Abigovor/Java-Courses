package lesson_6_homework;

import lesson_6_homework.UserException.UserException;
import lesson_6_homework.UserException.UserPetException;

/**
 * Класс описывает клинику
 * Created by Single on 18.04.2015.
 */
public class Clinic {
    /**
     * Список клиентов
     */
    private final Client[] clients;

    /**
     * Количество клиентов
     */
    private int size;

    public Clinic(final int size) {
        this.clients = new Client[size];
    }

    /**
     * Добавление клиента
     *
     * @param client Клиент
     */
    public void addClient(final Client client) {
        this.clients[size] = client;
        ++size;
    }

    /**
     * Поиск клиента по имени
     *
     * @param name Имя клиента
     * @return Клиент
     */
    public Client findClientByName(final String name) throws UserException {
        int i = 0;
        if (!isEmpty()) {
            for (; i < size; i++) {
                if (clients[i].getName().equals(name))
                    break;
                else
                    throw new UserException("The customer is not found");
            }
        } else
            throw new UserException("Client list is empty!");
        return clients[i];
    }

    /**
     * Поиск клиента по кличке пит омца
     *
     * @param name Кличка питомца
     * @return Клиент
     */
    public Client findClientByPetName(final String name) throws UserException, UserPetException {
        int i = 0;
        if (!isEmpty()) {
            for (; i < size; i++) {
                if (clients[i].getPet().getName().equals(name)) {
                    break;
                } else
                    throw new UserPetException("The customer is not found");
            }
        } else
            throw new UserException("Client list is empty!");
        return clients[i];
    }

    /**
     * Редактируем имя клиента
     *
     * @param name    Имя
     * @param newName Новое имя
     */
    public Client renameClient(String name, String newName) throws UserException {
        Client client = this.findClientByName(name);
        client.setName(newName);
        return client;
    }

    /**
     * Редактируем кличку питомца
     *
     * @param name    Кличка
     * @param newName Новая кличка
     */
    public Client renamePet(String name, String newName) throws UserException, UserPetException {
        Client client = this.findClientByPetName(name);
        // Если у клиента есть питомец
        if (!(client.getPet().getName().equals("")))
            client.getPet().setName(newName);   // устанавливаем новое имя
        else
            throw new UserPetException("Client does not have a pet!");
        return client;
    }


    /**
     * Удаление клиента по имени
     *
     * @param name Имя клиента
     * @throws UserException Если клиента нет, выкидывается исключение
     */
    public void removeClient(String name) throws UserException {
        int i = 0;
        if (!isEmpty()) {
            for (; i < size; i++) {
                if (clients[i].getName().equals(name))
                    if (i == size) {
                        size--;
                        break;
                    } else {
                        if (i + 1 <= size)
                            while (i != size) {
                                clients[i] = clients[i + 1];
                                i++;
                            }
                        size--;
                        break;
                    }
                else
                    throw new UserException("The customer is not found");
            }
        } else
            throw new UserException("Client list is empty!");
    }

    /**
     * Удаление питомца у клиента
     *
     * @param nameClient Имя клиента
     * @throws UserException Если клиента нет, выкидывается исключение
     */
    public void removePet(String nameClient) throws UserException {
        Client client = findClientByName(nameClient);
        client.setName("");
    }

    /**
     * Проверка на наличие клиентов
     *
     * @return true если списток пуст
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Показываем всех клиентов
     */
    public void showClient() {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++)
                System.out.println(clients[i].toString());
        } else
            System.out.println("Client list is empty!");
    }
}
