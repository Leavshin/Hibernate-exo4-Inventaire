package org.example.service;

import org.example.entity.Vente;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class RapportService {

    public List<Vente> getVentesParProduit(Long articleId) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.createQuery("from Vente where article.id = :articleId", Vente.class)
                    .setParameter("articleId", articleId)
                    .list();
        }
    }

    public List<Vente> getVentesParPeriode(LocalDate startDate, LocalDate endDate) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.createQuery("from Vente where date >= :startDate and date <= :endDate", Vente.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .list();
        }
    }

    public List<Vente> getVentesParClient(Long clientId) {
        try (Session session = SessionfactorySingleton.getSessionFactory().openSession()) {
            return session.createQuery("from Vente where client.id = :clientId", Vente.class)
                    .setParameter("clientId", clientId)
                    .list();
        }
    }
}
