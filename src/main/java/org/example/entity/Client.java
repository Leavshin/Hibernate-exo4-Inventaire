package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private int age;
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Vente> historiqueDesAchats;

    public Client(String nom, String prenom, int age, String email, List<Vente> historiqueDesAchats) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.historiqueDesAchats = historiqueDesAchats;
    }
}
