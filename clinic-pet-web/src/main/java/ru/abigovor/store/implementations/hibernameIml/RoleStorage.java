package ru.abigovor.store.implementations.hibernameIml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.abigovor.models.Role;
import ru.abigovor.store.command.HibernateTransaction;
import ru.abigovor.store.dao.RoleDAO;

import java.util.Collection;
import java.util.List;

@Transactional
@Repository
public class RoleStorage extends HibernateTransaction implements RoleDAO {
    private final HibernateTemplate template;

    @Autowired
    public RoleStorage(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Role> values() {
        return (List<Role>) this.template.find("from Role");
    }

    @Override
    public int add(Role role) {
        return (int) this.template.save(role);
    }

    @Override
    public void edit(Role role) {
        this.template.update(role);
    }

    @Override
    public void delete(int id) {
        this.template.delete(get(id));
    }

    @Override
    public Role get(int id) {
        return this.template.get(Role.class, id);
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
    }
}
