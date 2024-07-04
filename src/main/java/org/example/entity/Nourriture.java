package org.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("NOURRITURE")
public class Nourriture extends Article {

    @Column(name = "date_peremption")
    private LocalDate dateDePeremption;

    public Nourriture(Long id, String description, double prix, int quantiteEnStock, LocalDate dateDeRestock, LocalDate dateDePeremption) {
        super(id, description, prix, quantiteEnStock, dateDeRestock);
        this.dateDePeremption = dateDePeremption;
    }

    public Nourriture() {

    }
}
