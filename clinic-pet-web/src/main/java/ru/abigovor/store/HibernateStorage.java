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

    public HibernateStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<Client> values() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from Client").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<Role> roles() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from Role").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int add(Client client) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(client);
            return client.getId();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void edit(Client client) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(client);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(get(id));
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Client get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (Client) session.get(Client.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Client findByEmail(String email) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("from Client as client where client.email= :email");
            query.setParameter("email", email);
            List<Client> clients = query.list();
            if (!clients.isEmpty())
                return (Client) query.list().get(0);
            return null;
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Collection<Client> findByName(String name) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("from Client as client where client.name= :first_name");
            query.setParameter("first_name", name);
            List<Client> users = query.list();
            if (users.isEmpty())
                throw new IllegalStateException("User not found!");
            return users;
        } finally {
            tx.commit();
            session.close();
        }
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

class Main {
    public static void main(String[] args) {
        HibernateStorage storage = new HibernateStorage();

        Client client = storage.get(1);
        System.out.println(client.getPet().getName());
    }
}