package ru.abigovor.store;

import main.ru.abigovor.Cat;
import main.ru.abigovor.Dog;
import main.ru.abigovor.Pet;
import ru.abigovor.models.Client;
import ru.abigovor.service.Settings;
import ru.abigovor.store.DAO.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcStorage implements Storage<Client> {
    private final Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        final String driverClass = settings.value("jdbc.driver_class");
        try {
            Class.forName(driverClass);
            this.connection = DriverManager.getConnection(
                    settings.value("jdbc.url"),
                    settings.value("jdbc.username"),
                    settings.value("jdbc.password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't initialize driver: " + settings.value("jdbc.driver_class"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Collection<Client> values() {
        final List<Client> users = new ArrayList<>();
        try (final Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from client as cl\n" +
                     "left join pet as pet on pet.client_id = cl.uid;")) {
            while (rs.next()) {
                Pet pet = null;
                if (("Dog").equals(rs.getString("type_of_pet")))
                    pet = new Dog(rs.getString("nick"));
                else if (("Cat").equals(rs.getString("type_of_pet")))
                    pet = new Cat(rs.getString("nick"));

                users.add(new Client(
                        rs.getInt("uid"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("password"),
                        rs.getString("sex").charAt(0), pet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int add(Client client) {
        try (PreparedStatement statement = this.connection.prepareStatement("insert into client(first_name, second_name, password, sex, role) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getPassword());
            statement.setString(4, String.valueOf(client.getSex()));
            statement.setInt(5, 1);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int key = generatedKeys.getInt(1);
                    addPet(client.getPet(), key);
                    return key;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new user");
    }

    public int addPet(Pet pet, int id_of_client) {
        if (null != pet) {
            try (PreparedStatement statement = this.connection.prepareStatement("insert into pet (nick, sex, type_of_pet, client_id) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, pet.getName());
                statement.setString(2, "f");
                statement.setString(3, pet.getClazz());
                statement.setInt(4, id_of_client);
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new IllegalStateException("Could not create new user");
        }
        return 0;
    }

    @Override
    public void edit(Client client) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE client SET first_name = ?, second_name = ?, sex = ? WHERE uid = ?")) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, String.valueOf(client.getSex()));
            statement.setInt(4, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        deletePet(id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM client WHERE uid = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0)
                throw new IllegalStateException(String.format("User %s does not exists", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePet(int userID) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM pet WHERE client_id = ?")) {
            statement.setInt(1, userID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client get(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM client as cl\n" +
                "LEFT JOIN pet AS pet ON pet.client_id = cl.uid\n" +
                "WHERE cl.uid = ?;")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Pet pet = null;
                    if (("Dog").equals(rs.getString("type_of_pet")))
                        pet = new Dog(rs.getString("nick"));
                    else if (("Cat").equals(rs.getString("type_of_pet")))
                        pet = new Cat(rs.getString("nick"));

                    return new Client(
                            rs.getInt("uid"),
                            rs.getString("first_name"),
                            rs.getString("second_name"),
                            rs.getString("password"),
                            rs.getString("sex").charAt(0),
                            pet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Client with id %s not found", id));
    }


    public Client findByEmail(String email) {
        return null;
    }

    public Collection<Client> findByName(String name) {
        List<Client> foundUsers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE first_name ilike ?")) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    foundUsers.add(new Client(
                            rs.getInt("uid"),
                            rs.getString("first_name"),
                            rs.getString("second_name"),
                            rs.getString("password"),
                            rs.getString("sex").charAt(0),
                            null));
                }
            }
            if (foundUsers.isEmpty())
                throw new IllegalStateException(String.format("Client %S not found", name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUsers;
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}