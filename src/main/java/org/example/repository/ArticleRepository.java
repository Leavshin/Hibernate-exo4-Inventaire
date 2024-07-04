package org.example.repository;

import org.example.entity.Article;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ArticleRepository {

    public void save(Article article) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(article);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Article article) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(article);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Optional<Article> findById(Long id) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            Article article = session.get(Article.class, id);
            return Optional.ofNullable(article);
        }
    }

    public List<Article> findAll() {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Article");
            return query.getResultList();
        }
    }

    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Article article = session.get(Article.class, id);
            if (article != null) {
                session.delete(article);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void restock(Long id, int quantite) {
        Transaction transaction = null;
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Article article = session.get(Article.class, id);
            if (article != null) {
                article.setQuantiteEnStock(article.getQuantiteEnStock() + quantite);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Article> findByType(String type) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            Query query;
            switch (type) {
                case "Nourriture":
                    query = session.createQuery("FROM Nourriture");
                    break;
                case "Electronique":
                    query = session.createQuery("FROM Electronique");
                    break;
                case "Mode":
                    query = session.createQuery("FROM Mode");
                    break;
                default:
                    throw new IllegalArgumentException("Type d'article invalide : " + type);
            }
            return query.getResultList();
        }
    }

    public void close() {
        SessionfactorySingleton.getSessionFactory().close();
    }
}
