package ru.abigovor.store.implementations.hibernameIml;

import org.hibernate.Query;
import org.hibernate.Session;
import ru.abigovor.models.Client;
import ru.abigovor.store.dao.UserDAO;
import ru.abigovor.store.command.HibernateTransaction;

import java.util.List;

public class UserStorage extends HibernateTransaction implements UserDAO {

    @Override
    public Client findByEmail(String email) {
        return super.execute(
                (Session session) -> {
                    final Query query = session.createQuery("from Client as client where lower(client.email) like :email");
                    query.setString("email", email);
                    List<Client> users = query.list();
                    return users.isEmpty() ? null : users.iterator().next();
                }
        );
    }

    @Override
    public List<Client> findByName(String name) {
        return super.execute(new Command<List<Client>>() {
            @Override
            public List<Client> process(Session session) {
                final Query query = session.createQuery("from Client as client where lower(client.name) like lower(:name)");
                query.setParameter("name", "%" + name + "%");
                return query.list();
            }
        });
    }

    @Override
    public List<Client> findByRoleName(String role) {
        return super.execute((Session session) -> {
            final Query query = session.createQuery("select client from Client client inner join client.role role on lower(role.name)=lower(:name)");
            query.setParameter("name", role);
            return (List<Client>) query.list();
        });
    }

    @Override
    public List<Client> values() {
        return super.execute(
                (Session session) -> session.createQuery("from Client").list()
        );
    }

    @Override
    public int add(Client client) {
        return super.execute(
                (Session session) -> {
                    session.save(client);
                    return client.getId();
                }
        );
    }

    @Override
    public void edit(Client client) {
        super.execute(
                (Session session) -> {
                    session.update(client);
                    return null;
                }
        );
    }

    @Override
    public void delete(int id) {
        super.execute(
                (Session session) -> {
                    session.delete(get(id));
                    return null;
                }
        );
    }

    @Override
    public Client get(int id) {
        return super.execute((Session session) -> (Client) session.get(Client.class, id));
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
    }
}
