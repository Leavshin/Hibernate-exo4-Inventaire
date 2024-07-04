package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.EtatVente;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vente")
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id")
    private Long articleId;

    @ManyToOne
    @JoinColumn(name = "client_id") // Nom de la colonne qui fait référence à l'entité Client
    private Client client;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "quantity")
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private EtatVente etat;

    public Vente(Long articleId, int quantity, Client client) {
        this.articleId = articleId;
        this.quantity = quantity;
        this.client = client;
        this.date = LocalDate.now();
        this.etat = EtatVente.EN_COURS;
    }

    public Vente(Long articleId, int quantity, Long clientId) {
    }
}
