package ru.abigovor.store.command;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.abigovor.utils.HibernateUtil;

public abstract class HibernateTransaction {
    protected SessionFactory factory = HibernateUtil.getFactory();

    public interface Command<T> {
        T process(Session session);
    }

    public <T> T execute(final Command<T> command) {
        Session session = this.factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.process(session);
        } finally {
            tx.commit();
            session.close();
        }
    }
}
