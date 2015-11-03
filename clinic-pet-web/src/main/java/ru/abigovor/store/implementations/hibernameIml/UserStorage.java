package ru.abigovor.store.implementations.hibernameIml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.abigovor.models.Client;
import ru.abigovor.store.command.HibernateTransaction;
import ru.abigovor.store.dao.UserDAO;

import java.util.List;

@Transactional
@Repository
public class UserStorage extends HibernateTransaction implements UserDAO {

    private final HibernateTemplate template;

    @Autowired
    public UserStorage(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Client findByEmail(String email) {
        return (Client) this.template.find("from Client as client where lower(client.email)= lower(?)", email).iterator().next();
    }

    @Override
    public List<Client> findByName(String name) {
        return (List<Client>) this.template.find("from Client as client where lower(client.name) like lower(?)", name);
    }

    @Override
    public List<Client> findByRoleName(String role) {
        return (List<Client>) this.template.find("select client from Client client inner join client.role role ON role.name like ?", role.toLowerCase());
    }

    @Override
    public List<Client> values() {
        return (List<Client>) this.template.find("from Client");
    }

    @Override
    public int add(Client client) {
        return (int) this.template.save(client);
    }

    @Override
    public void edit(Client client) {
        this.template.update(client);
    }

    @Override
    public void delete(int id) {
        this.template.delete(get(id));
    }

    @Override
    public Client get(int id) {
        return (Client) this.template.get(Client.class, id);
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
    }
}
