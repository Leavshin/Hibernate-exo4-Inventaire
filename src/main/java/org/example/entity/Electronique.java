package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ELECTRONIQUE")
public class Electronique extends Article {
    private int dureeDeBatterie;

    public Electronique(Long id, String description, double prix, int quantiteEnStock, LocalDate dateDeRestock, int dureeDeBatterie) {
        super(id, description, prix, quantiteEnStock, dateDeRestock);
        this.dureeDeBatterie = dureeDeBatterie;
    }
}
