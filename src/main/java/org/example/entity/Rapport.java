package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rapport")
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private LocalDate dateAnalyse;

    public Rapport(String titre, String description, LocalDate dateAnalyse) {
        this.titre = titre;
        this.description = description;
        this.dateAnalyse = dateAnalyse;
    }

}
