package org.example.controller;

import org.example.service.RapportService;

import java.time.LocalDate;
import java.util.Scanner;

public class IHMAnalyse {
    private final RapportService analyseService = new RapportService();
    private final Scanner scanner;

    public IHMAnalyse(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("Rapports et analyses");
            System.out.println("1. Ventes par produit");
            System.out.println("2. Ventes par période");
            System.out.println("3. Ventes par client");
            System.out.println("4. Retour");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ventesParProduit();
                    break;
                case 2:
                    ventesParPeriode();
                    break;
                case 3:
                    ventesParClient();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void ventesParProduit() {
        System.out.println("ID du produit:");
        Long productId = scanner.nextLong();
        scanner.nextLine();

        analyseService.getVentesParProduit(productId).forEach(System.out::println);
    }

    private void ventesParPeriode() {
        System.out.println("Date de début (AAAA-MM-JJ):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Date de fin (AAAA-MM-JJ):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        analyseService.getVentesParPeriode(startDate, endDate).forEach(System.out::println);
    }

    private void ventesParClient() {
        System.out.println("ID du client:");
        Long clientId = scanner.nextLong();
        scanner.nextLine();

        analyseService.getVentesParClient(clientId).forEach(System.out::println);
    }
}
