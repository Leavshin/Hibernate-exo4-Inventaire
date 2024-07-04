package org.example.controller;

import org.example.entity.Mode;
import org.example.service.ModeService;
import org.example.util.CategorieMode;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class IHMMode {
    private final ModeService modeService;
    private final Scanner scanner;

    public IHMMode(Scanner scanner) {
        this.scanner = scanner;
        this.modeService = new ModeService();
    }

    public void start() {
        while (true) {
            System.out.println("---- Gestion des articles de mode ----");
            System.out.println("1. Ajouter un article de mode");
            System.out.println("2. Modifier un article de mode");
            System.out.println("3. Supprimer un article de mode");
            System.out.println("4. Consulter tous les articles de mode");
            System.out.println("5. Retour");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMode();
                    break;
                case 2:
                    updateMode();
                    break;
                case 3:
                    deleteMode();
                    break;
                case 4:
                    viewAllModes();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void addMode() {
        System.out.println("Ajouter un article de mode");

        System.out.print("Description : ");
        String description = scanner.nextLine();

        System.out.print("Prix : ");
        double prix = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Quantité en stock : ");
        int quantiteEnStock = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Date de restock (YYYY-MM-DD) : ");
        String dateRestockStr = scanner.nextLine();
        LocalDate dateRestock = LocalDate.parse(dateRestockStr);

        System.out.print("Catégorie (HOMME, FEMME, ENFANT) : ");
        String categorieStr = scanner.nextLine();
        CategorieMode categorie = CategorieMode.valueOf(categorieStr.toUpperCase());

        System.out.print("Taille : ");
        String taille = scanner.nextLine();

        Mode mode = new Mode(null, description, prix, quantiteEnStock, dateRestock, categorie, taille);
        modeService.addMode(mode);

        System.out.println("Article de mode ajouté avec succès !");
    }

    private void updateMode() {
        System.out.println("Modifier un article de mode");

        System.out.print("ID de l'article de mode à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Mode existingMode = modeService.findModeById(id);
        if (existingMode == null) {
            System.out.println("Aucun article de mode trouvé avec l'ID : " + id);
            return;
        }

        System.out.print("Nouvelle description (" + existingMode.getDescription() + ") : ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) {
            existingMode.setDescription(newDescription);
        }

        System.out.print("Nouveau prix (" + existingMode.getPrix() + ") : ");
        String prixStr = scanner.nextLine();
        if (!prixStr.isEmpty()) {
            double newPrix = Double.parseDouble(prixStr);
            existingMode.setPrix(newPrix);
        }

        System.out.print("Nouvelle quantité en stock (" + existingMode.getQuantiteEnStock() + ") : ");
        String quantiteEnStockStr = scanner.nextLine();
        if (!quantiteEnStockStr.isEmpty()) {
            int newQuantiteEnStock = Integer.parseInt(quantiteEnStockStr);
            existingMode.setQuantiteEnStock(newQuantiteEnStock);
        }

        System.out.print("Nouvelle date de restock (YYYY-MM-DD) (" + existingMode.getDateDeRestock() + ") : ");
        String dateRestockStr = scanner.nextLine();
        if (!dateRestockStr.isEmpty()) {
            LocalDate newDateRestock = LocalDate.parse(dateRestockStr);
            existingMode.setDateDeRestock(newDateRestock);
        }

        System.out.print("Nouvelle catégorie (" + existingMode.getCategorie() + ") : ");
        String categorieStr = scanner.nextLine();
        if (!categorieStr.isEmpty()) {
            CategorieMode newCategorie = CategorieMode.valueOf(categorieStr.toUpperCase());
            existingMode.setCategorie(newCategorie);
        }

        System.out.print("Nouvelle taille (" + existingMode.getTaille() + ") : ");
        String newTaille = scanner.nextLine();
        if (!newTaille.isEmpty()) {
            existingMode.setTaille(newTaille);
        }

        modeService.updateMode(existingMode);
        System.out.println("Article de mode mis à jour avec succès !");
    }

    private void deleteMode() {
        System.out.println("Supprimer un article de mode");

        System.out.print("ID de l'article de mode à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Mode existingMode = modeService.findModeById(id);
        if (existingMode == null) {
            System.out.println("Aucun article de mode trouvé avec l'ID : " + id);
            return;
        }

        modeService.deleteMode(id);
        System.out.println("Article de mode supprimé avec succès !");
    }

    private void viewAllModes() {
        System.out.println("Liste des articles de mode");

        List<Mode> modes = modeService.findAllModes();
        if (modes.isEmpty()) {
            System.out.println("Aucun article de mode trouvé.");
        } else {
            for (Mode mode : modes) {
                System.out.println(mode);
            }
        }
    }
}
