package org.example.repository;

import org.example.entity.Mode;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ModeRepository {

    public void save(Mode mode) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(mode);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Mode mode) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(mode);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Mode mode) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(mode);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Mode findById(Long id) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.get(Mode.class, id);
        }
    }

    public List<Mode> findAll() {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.createQuery("from Mode", Mode.class).list();
        }
    }
}
