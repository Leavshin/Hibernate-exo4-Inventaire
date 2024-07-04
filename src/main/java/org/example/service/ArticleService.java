package org.example.service;

import org.example.entity.Article;
import org.example.entity.Electronique;
import org.example.entity.Mode;
import org.example.entity.Nourriture;
import org.example.repository.ArticleRepository;
import org.example.util.CategorieMode;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService() {
        this.articleRepository = new ArticleRepository();
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void update(Article article) {
        articleRepository.update(article);
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    // Méthode pour restocker un article par son ID
    public void restock(Long id, int quantite) {
        articleRepository.restock(id, quantite);
    }

    // Méthode spécifique pour ajouter un article de type Nourriture
    public void addNourriture(String description, double prix, int quantiteEnStock, LocalDate dateDePeremption) {
        Nourriture nourriture = new Nourriture();
        nourriture.setDescription(description);
        nourriture.setPrix(prix);
        nourriture.setQuantiteEnStock(quantiteEnStock);
        nourriture.setDateDeRestock(LocalDate.now());
        nourriture.setDateDePeremption(dateDePeremption);

        articleRepository.save(nourriture);
    }

    // Méthode spécifique pour ajouter un article de type Electronique
    public void addElectronique(String description, double prix, int quantiteEnStock, int dureeDeBatterie) {
        Electronique electronique = new Electronique();
        electronique.setDescription(description);
        electronique.setPrix(prix);
        electronique.setQuantiteEnStock(quantiteEnStock);
        electronique.setDateDeRestock(LocalDate.now());
        electronique.setDureeDeBatterie(dureeDeBatterie);

        articleRepository.save(electronique);
    }

    // Méthode spécifique pour ajouter un article de type Mode
    public void addMode(String description, double prix, int quantiteEnStock, CategorieMode categorie, String taille) {
        Mode mode = new Mode();
        mode.setDescription(description);
        mode.setPrix(prix);
        mode.setQuantiteEnStock(quantiteEnStock);
        mode.setDateDeRestock(LocalDate.now());
        mode.setCategorie(categorie);
        mode.setTaille(taille);

        articleRepository.save(mode);
    }
}
