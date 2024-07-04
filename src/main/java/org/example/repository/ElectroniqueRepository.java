package org.example.repository;

import org.example.entity.Electronique;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ElectroniqueRepository {

    public void save(Electronique electronique) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(electronique);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Electronique electronique) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(electronique);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Electronique electronique) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(electronique);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Electronique findById(Long id) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.get(Electronique.class, id);
        }
    }

    public List<Electronique> findAll() {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.createQuery("from Electronique", Electronique.class).list();
        }
    }
}
