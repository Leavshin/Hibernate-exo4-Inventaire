package org.example.controller;

import org.example.entity.Nourriture;
import org.example.service.NourritureService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class IHMNourriture {
    private final NourritureService nourritureService;
    private final Scanner scanner;

    public IHMNourriture(Scanner scanner) {
        this.scanner = scanner;
        this.nourritureService = new NourritureService();
    }

    public void start() {
        while (true) {
            System.out.println("---- Gestion des articles de nourriture ----");
            System.out.println("1. Ajouter un article de nourriture");
            System.out.println("2. Modifier un article de nourriture");
            System.out.println("3. Supprimer un article de nourriture");
            System.out.println("4. Consulter tous les articles de nourriture");
            System.out.println("5. Retour");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNourriture();
                    break;
                case 2:
                    updateNourriture();
                    break;
                case 3:
                    deleteNourriture();
                    break;
                case 4:
                    viewAllNourritures();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void addNourriture() {
        System.out.println("Ajouter un article de nourriture");

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

        System.out.print("Date de péremption (YYYY-MM-DD) : ");
        String datePeremptionStr = scanner.nextLine();
        LocalDate datePeremption = LocalDate.parse(datePeremptionStr);

        Nourriture nourriture = new Nourriture(null, description, prix, quantiteEnStock, dateRestock, datePeremption);
        nourritureService.addNourriture(nourriture);

        System.out.println("Article de nourriture ajouté avec succès !");
    }

    private void updateNourriture() {
        System.out.println("Modifier un article de nourriture");

        System.out.print("ID de l'article de nourriture à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Nourriture existingNourriture = nourritureService.findNourritureById(id);
        if (existingNourriture == null) {
            System.out.println("Aucun article de nourriture trouvé avec l'ID : " + id);
            return;
        }

        System.out.print("Nouvelle description (" + existingNourriture.getDescription() + ") : ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) {
            existingNourriture.setDescription(newDescription);
        }

        System.out.print("Nouveau prix (" + existingNourriture.getPrix() + ") : ");
        String prixStr = scanner.nextLine();
        if (!prixStr.isEmpty()) {
            double newPrix = Double.parseDouble(prixStr);
            existingNourriture.setPrix(newPrix);
        }

        System.out.print("Nouvelle quantité en stock (" + existingNourriture.getQuantiteEnStock() + ") : ");
        String quantiteEnStockStr = scanner.nextLine();
        if (!quantiteEnStockStr.isEmpty()) {
            int newQuantiteEnStock = Integer.parseInt(quantiteEnStockStr);
            existingNourriture.setQuantiteEnStock(newQuantiteEnStock);
        }

        System.out.print("Nouvelle date de restock (YYYY-MM-DD) (" + existingNourriture.getDateDeRestock() + ") : ");
        String dateRestockStr = scanner.nextLine();
        if (!dateRestockStr.isEmpty()) {
            LocalDate newDateRestock = LocalDate.parse(dateRestockStr);
            existingNourriture.setDateDeRestock(newDateRestock);
        }

        System.out.print("Nouvelle date de péremption (YYYY-MM-DD) (" + existingNourriture.getDateDePeremption() + ") : ");
        String datePeremptionStr = scanner.nextLine();
        if (!datePeremptionStr.isEmpty()) {
            LocalDate newDatePeremption = LocalDate.parse(datePeremptionStr);
            existingNourriture.setDateDePeremption(newDatePeremption);
        }

        nourritureService.updateNourriture(existingNourriture);
        System.out.println("Article de nourriture mis à jour avec succès !");
    }

    private void deleteNourriture() {
        System.out.println("Supprimer un article de nourriture");

        System.out.print("ID de l'article de nourriture à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Nourriture existingNourriture = nourritureService.findNourritureById(id);
        if (existingNourriture == null) {
            System.out.println("Aucun article de nourriture trouvé avec l'ID : " + id);
            return;
        }

        nourritureService.deleteNourriture(id);
        System.out.println("Article de nourriture supprimé avec succès !");
    }

    private void viewAllNourritures() {
        System.out.println("Liste des articles de nourriture");

        List<Nourriture> nourritures = nourritureService.findAllNourritures();
        if (nourritures.isEmpty()) {
            System.out.println("Aucun article de nourriture trouvé.");
        } else {
            for (Nourriture nourriture : nourritures) {
                System.out.println(nourriture);
            }
        }
    }
}
