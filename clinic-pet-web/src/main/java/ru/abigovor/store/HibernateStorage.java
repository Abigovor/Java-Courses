package ru.abigovor.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.abigovor.models.Client;
import ru.abigovor.store.DAO.Storage;

import java.util.Collection;

public class HibernateStorage implements Storage<Client> {
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
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}