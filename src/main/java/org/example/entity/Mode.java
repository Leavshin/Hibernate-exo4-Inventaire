package org.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.util.CategorieMode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("MODE")
public class Mode extends Article {

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie_mode")
    private CategorieMode categorie;

    private String taille;

    public Mode(Long id, String description, double prix, int quantiteEnStock, LocalDate dateDeRestock, CategorieMode categorie, String taille) {
        super(id, description, prix, quantiteEnStock, dateDeRestock);
        this.categorie = categorie;
        this.taille = taille;
    }

    public Mode() {

    }
}
