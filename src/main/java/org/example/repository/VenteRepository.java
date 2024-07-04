package org.example.repository;

import org.example.entity.Vente;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VenteRepository {

    public void save(Vente vente) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(vente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Vente vente) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(vente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Vente vente) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(vente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Vente> findAll() {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.createQuery("from Vente", Vente.class).list();
        }
    }

    public Vente findById(Long id) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.get(Vente.class, id);
        }
    }

    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Vente vente = session.get(Vente.class, id);
            if (vente != null) {
                session.delete(vente);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
