package org.example.controller;

import org.example.entity.Article;
import org.example.entity.Electronique;
import org.example.entity.Mode;
import org.example.entity.Nourriture;
import org.example.service.ArticleService;
import org.example.util.CategorieMode;

import java.time.LocalDate;
import java.util.Scanner;

public class IHMArticle {
    private final ArticleService articleService;
    private final Scanner scanner;

    public IHMArticle(Scanner scanner) {
        this.scanner = scanner;
        this.articleService = new ArticleService();
    }

    public void start() {
        while (true) {
            System.out.println("---- Gestion de l'inventaire ----");
            System.out.println("1. Ajouter un article");
            System.out.println("2. Modifier un article");
            System.out.println("3. Supprimer un article");
            System.out.println("4. Consulter les articles");
            System.out.println("5. Restocker un article");
            System.out.println("6. Retour");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addArticle();
                    break;
                case 2:
                    updateArticle();
                    break;
                case 3:
                    deleteArticle();
                    break;
                case 4:
                    viewArticles();
                    break;
                case 5:
                    restockArticle();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void addArticle() {
        System.out.println("Type d'article (1: Nourriture, 2: Electronique, 3: Mode):");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Description de l'article:");
        String description = scanner.nextLine();
        System.out.println("Prix:");
        double prix = scanner.nextDouble();
        System.out.println("Quantité en stock:");
        int quantiteEnStock = scanner.nextInt();
        scanner.nextLine();

        Article article;
        switch (type) {
            case 1:
                article = new Nourriture();
                System.out.println("Date de péremption (AAAA-MM-JJ):");
                LocalDate dateDePeremption = LocalDate.parse(scanner.nextLine());
                ((Nourriture) article).setDateDePeremption(dateDePeremption);
                break;
            case 2:
                article = new Electronique();
                System.out.println("Durée de batterie (heures):");
                int dureeBatterie = scanner.nextInt();
                scanner.nextLine();
                ((Electronique) article).setDureeDeBatterie(dureeBatterie);
                break;
            case 3:
                article = new Mode();
                System.out.println("Catégorie (HOMME, FEMME, ENFANT):");
                String categorie = scanner.nextLine();
                System.out.println("Taille:");
                String taille = scanner.nextLine();
                ((Mode) article).setCategorie(CategorieMode.valueOf(categorie));
                ((Mode) article).setTaille(taille);
                break;
            default:
                System.out.println("Type d'article invalide.");
                return;
        }

        article.setDescription(description);
        article.setPrix(prix);
        article.setQuantiteEnStock(quantiteEnStock);
        article.setDateDeRestock(LocalDate.now());
        articleService.save(article);

        System.out.println("Article ajouté avec succès !");
    }

    private void updateArticle() {
        System.out.println("ID de l'article à modifier:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        articleService.findById(id).ifPresent(article -> {
            System.out.println("Nouvelle description (laissez vide pour ne pas modifier):");
            String description = scanner.nextLine();
            if (!description.isEmpty()) {
                article.setDescription(description);
            }
            System.out.println("Nouveau prix (0 pour ne pas modifier):");
            double prix = scanner.nextDouble();
            if (prix != 0) {
                article.setPrix(prix);
            }
            System.out.println("Nouvelle quantité en stock (0 pour ne pas modifier):");
            int quantiteEnStock = scanner.nextInt();
            if (quantiteEnStock != 0) {
                article.setQuantiteEnStock(quantiteEnStock);
            }
            scanner.nextLine();

            articleService.update(article);
            System.out.println("Article modifié avec succès !");
        });
    }

    private void deleteArticle() {
        System.out.println("ID de l'article à supprimer:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        articleService.deleteById(id);
        System.out.println("Article supprimé avec succès !");
    }

    private void viewArticles() {
        articleService.findAll().forEach(System.out::println);
    }

    private void restockArticle() {
        System.out.println("ID de l'article à restocker:");
        Long id = scanner.nextLong();
        System.out.println("Quantité à ajouter:");
        int quantite = scanner.nextInt();
        scanner.nextLine();

        articleService.restock(id, quantite);
        System.out.println("Article restocké avec succès !");
    }
}
