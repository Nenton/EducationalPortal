package ru.innopolis.stc9.earth_stc9.db.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.earth_stc9.db.entities.Role;

@Repository
public class RoleDaoHibernate {
    private final SessionFactory factory;

    @Autowired
    public RoleDaoHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    public Role getRoleById(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Role role = session.get(Role.class, id);
            session.getTransaction().commit();
            return role;
        }
    }

    public boolean addRole(Role role) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        }
        return true;
    }

    public void deleteRole(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Role role = session.get(Role.class, id);
            session.delete(role);
            session.getTransaction().commit();
        }
    }

    public void update(Role role) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(role);
            session.getTransaction().commit();
        }
    }
}
