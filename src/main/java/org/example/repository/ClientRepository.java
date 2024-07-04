package org.example.repository;

import org.example.entity.Client;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ClientRepository {

    public Client save(Client client) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return client;
    }

    public Client update(Client client) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            client = (Client) session.merge(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return client;
    }

    public Optional<Client> findById(Long id) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            Client client = session.get(Client.class, id);
            return Optional.ofNullable(client);
        }
    }

    public List<Client> findAll() {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Client");
            return query.getResultList();
        }
    }

    public boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.delete(client);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        SessionfactorySingleton.getSessionFactory().close();
    }
}
