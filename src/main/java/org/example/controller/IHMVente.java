package org.example.controller;

import org.example.entity.Vente;
import org.example.service.VenteService;

import java.util.List;
import java.util.Scanner;

public class IHMVente {
    private final VenteService venteService;
    private final Scanner scanner;

    public IHMVente(Scanner scanner) {
        this.scanner = scanner;
        this.venteService = new VenteService();
    }

    public void start() {
        while (true) {
            System.out.println("---- Gestion des ventes ----");
            System.out.println("1. Enregistrer une vente");
            System.out.println("2. Modifier une vente");
            System.out.println("3. Annuler une vente");
            System.out.println("4. Consulter les ventes");
            System.out.println("5. Retour");

            String entry = scanner.nextLine();

            switch (entry) {
                case "1":
                    addSale();
                    break;
                case "2":
                    updateSale();
                    break;
                case "3":
                    cancelSale();
                    break;
                case "4":
                    viewSales();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void addSale() {
        System.out.println("Enregistrer une vente");

        System.out.print("ID de l'article : ");
        Long articleId = Long.parseLong(scanner.nextLine());

        System.out.print("Quantité : ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("ID du client : ");
        Long clientId = Long.parseLong(scanner.nextLine());

        // Créer une vente avec le constructeur simplifié
        Vente vente = new Vente(articleId, quantity, clientId);

        venteService.addSale(vente);
        System.out.println("Vente enregistrée avec succès.");
    }

    private void updateSale() {
        System.out.println("Modifier une vente");
        System.out.print("ID de la vente à modifier : ");
        Long venteId = Long.parseLong(scanner.nextLine());

        Vente vente = venteService.getSaleById(venteId);
        if (vente == null) {
            System.out.println("Vente non trouvée.");
            return;
        }

        System.out.print("Nouvelle quantité : ");
        int quantity = Integer.parseInt(scanner.nextLine());

        vente.setQuantity(quantity);
        venteService.updateSale(vente);
        System.out.println("Vente modifiée avec succès.");
    }

    private void cancelSale() {
        System.out.println("Annuler une vente");
        System.out.print("ID de la vente à annuler : ");
        long venteId = Long.parseLong(scanner.nextLine());

        venteService.cancelSale(venteId);
        System.out.println("Vente annulée avec succès.");
    }

    private void viewSales() {
        System.out.println("Consulter les ventes");
        List<Vente> sales = venteService.getSales();

        for (Vente vente : sales) {
            System.out.println(vente);
        }
    }
}
