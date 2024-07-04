package org.example.controller;

import org.example.entity.Electronique;
import org.example.service.ElectroniqueService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class IHMElectronique {
    private final ElectroniqueService electroniqueService;
    private final Scanner scanner;

    public IHMElectronique(Scanner scanner) {
        this.scanner = scanner;
        this.electroniqueService = new ElectroniqueService();
    }

    public void start() {
        while (true) {
            System.out.println("---- Gestion des articles électroniques ----");
            System.out.println("1. Ajouter un article électronique");
            System.out.println("2. Modifier un article électronique");
            System.out.println("3. Supprimer un article électronique");
            System.out.println("4. Consulter tous les articles électroniques");
            System.out.println("5. Retour");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addElectronique();
                    break;
                case 2:
                    updateElectronique();
                    break;
                case 3:
                    deleteElectronique();
                    break;
                case 4:
                    viewAllElectroniques();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void addElectronique() {
        System.out.println("Ajouter un article électronique");

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

        System.out.print("Durée de batterie : ");
        int dureeDeBatterie = scanner.nextInt();
        scanner.nextLine();

        Electronique electronique = new Electronique(null, description, prix, quantiteEnStock, dateRestock, dureeDeBatterie);
        electroniqueService.addElectronique(electronique);

        System.out.println("Article électronique ajouté avec succès !");
    }

    private void updateElectronique() {
        System.out.println("Modifier un article électronique");

        System.out.print("ID de l'article électronique à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Electronique existingElectronique = electroniqueService.findElectroniqueById(id);
        if (existingElectronique == null) {
            System.out.println("Aucun article électronique trouvé avec l'ID : " + id);
            return;
        }

        System.out.print("Nouvelle description (" + existingElectronique.getDescription() + ") : ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) {
            existingElectronique.setDescription(newDescription);
        }

        System.out.print("Nouveau prix (" + existingElectronique.getPrix() + ") : ");
        String prixStr = scanner.nextLine();
        if (!prixStr.isEmpty()) {
            double newPrix = Double.parseDouble(prixStr);
            existingElectronique.setPrix(newPrix);
        }

        electroniqueService.updateElectronique(existingElectronique);
        System.out.println("Article électronique mis à jour avec succès !");
    }

    private void deleteElectronique() {
        System.out.println("Supprimer un article électronique");

        System.out.print("ID de l'article électronique à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Electronique existingElectronique = electroniqueService.findElectroniqueById(id);
        if (existingElectronique == null) {
            System.out.println("Aucun article électronique trouvé avec l'ID : " + id);
            return;
        }

        electroniqueService.deleteElectronique(id);
        System.out.println("Article électronique supprimé avec succès !");
    }

    private void viewAllElectroniques() {
        System.out.println("Liste des articles électroniques");

        List<Electronique> electroniques = electroniqueService.findAllElectroniques();
        if (electroniques.isEmpty()) {
            System.out.println("Aucun article électronique trouvé.");
        } else {
            for (Electronique electronique : electroniques) {
                System.out.println(electronique);
            }
        }
    }
}
