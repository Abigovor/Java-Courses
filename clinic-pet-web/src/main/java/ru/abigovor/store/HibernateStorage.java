package ru.abigovor.store;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.abigovor.models.Client;
import ru.abigovor.models.Role;

import java.util.Collection;
import java.util.List;

public class HibernateStorage implements Storage {
    private final SessionFactory factory;

    public interface Command<T> {
        T process(Session session);
    }

    private <T> T transaction(final Command<T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.process(session);
        } finally {
            tx.commit();
            session.close();
        }
    }

    public HibernateStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<Client> values() {
        return transaction(
                (Session session) -> session.createQuery("from Client").list()
        );
    }

    @Override
    public List<Role> roles() {
        return transaction(
                (Session session) -> session.createQuery("from Role").list()
        );
    }

    @Override
    public int add(Client client) {
        return transaction(
                (Session session) -> {
                    session.save(client);
                    return client.getId();
                }
        );
    }

    @Override
    public void edit(Client client) {
        transaction(
                (Session session) -> {
                    session.update(client);
                    return null;
                }
        );
    }

    @Override
    public void delete(int id) {
        transaction((Session session) -> {
                    session.delete(get(id));
                    return null;
                }
        );
    }

    @Override
    public Client get(int id) {
        return transaction(
                (Session session) -> {
                    return (Client) session.get(Client.class, id);
                }
        );
    }

    @Override
    public Client findByEmail(String email) {
        return transaction(
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
        return transaction(new Command<List<Client>>() {
            @Override
            public List<Client> process(Session session) {
                final Query query = session.createQuery("from Client as client where lower(client.name) like lower(:name)");
                query.setParameter("name", "%" + name + "%");
                return query.list();
            }
        });
    }

    public List<Client> findByRoleName(String roleName) {
        return transaction(new Command<List<Client>>() {
            @Override
            public List<Client> process(Session session) {
                final Query query = session.createQuery("select client from Client client join client.role role on lower(role.name)=lower(:name)");
                query.setString("name", roleName);
                return query.list();
            }
        });
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}