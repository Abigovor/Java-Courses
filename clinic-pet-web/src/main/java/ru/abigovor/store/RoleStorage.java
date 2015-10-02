package ru.abigovor.store;

import org.hibernate.Session;
import ru.abigovor.models.Role;
import ru.abigovor.store.DAO.Storage;
import ru.abigovor.store.command.Transaction;
import ru.abigovor.utils.HibernateUtil;

import java.util.Collection;

public class RoleStorage implements Storage<Role> {
    private final Transaction transaction;

    public RoleStorage() {
        this.transaction = new Transaction();
    }

    @Override
    public Collection<Role> values() {
        return transaction.execute((Session session) -> session.createQuery("from Role").list());
    }

    @Override
    public int add(Role role) {
        return transaction.execute((Session session) -> {
            session.save(role);
            return role.getId();
        });
    }

    @Override
    public void edit(Role role) {
        transaction.execute((Session session) -> {
            session.update(role);
            return null;
        });
    }

    @Override
    public void delete(int id) {
        transaction.execute((Session session) -> {
            session.delete(get(id));
            return null;
        });
    }

    @Override
    public Role get(int id) {
        return transaction.execute((Session session) -> (Role) session.get(Role.class, id));
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
    }
}
