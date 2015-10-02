package ru.abigovor.store.command;

import org.hibernate.Session;
import ru.abigovor.utils.HibernateUtil;

public class Transaction {
    public <T> T execute(final Command<T> command) {
        final Session session = HibernateUtil.getFactory().openSession();
        final org.hibernate.Transaction tx = session.beginTransaction();
        try {
            return command.process(session);
        } finally {
            tx.commit();
            session.close();
        }
    }
}
