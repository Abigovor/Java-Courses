package ru.abigovor.store.implementations.hibernameIml;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.abigovor.models.Role;
import ru.abigovor.store.command.HibernateTransaction;
import ru.abigovor.store.dao.RoleDAO;

import java.util.Collection;

@Repository
public class RoleStorage extends HibernateTransaction implements RoleDAO {

    @Override
    public Collection<Role> values() {
        return super.execute((Session session) -> session.createQuery("from Role").list());
    }

    @Override
    public int add(Role role) {
        return super.execute((Session session) -> {
            session.save(role);
            return role.getId();
        });
    }

    @Override
    public void edit(Role role) {
        super.execute((Session session) -> {
            session.update(role);
            return null;
        });
    }

    @Override
    public void delete(int id) {
        super.execute((Session session) -> {
            session.delete(get(id));
            return null;
        });
    }

    @Override
    public Role get(int id) {
        return super.execute((Session session) -> (Role) session.get(Role.class, id));
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
    }
}
