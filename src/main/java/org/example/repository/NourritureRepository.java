package org.example.repository;

import org.example.entity.Nourriture;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NourritureRepository {

    public void save(Nourriture nourriture) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(nourriture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Nourriture nourriture) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(nourriture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Nourriture nourriture) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nourriture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Nourriture findById(Long id) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.get(Nourriture.class, id);
        }
    }

    public List<Nourriture> findAll() {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.createQuery("from Nourriture", Nourriture.class).list();
        }
    }
}
