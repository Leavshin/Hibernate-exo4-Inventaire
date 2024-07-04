package org.example.controller;

import java.util.Scanner;

public class IHMGlobal {

    private final Scanner scanner;
    private final IHMArticle ihmArticle;
    private final IHMVente ihmVente;
    private final IHMClient ihmClient;
    private final IHMAnalyse ihmAnalyse;

    public IHMGlobal() {
        this.scanner = new Scanner(System.in);
        this.ihmArticle = new IHMArticle(scanner);
        this.ihmVente = new IHMVente(scanner);
        this.ihmClient = new IHMClient(scanner);
        this.ihmAnalyse = new IHMAnalyse(scanner);
    }

    public void start() {
        while (true) {
            System.out.println(" ---- Système de gestion -----");
            System.out.println("1. Gestion de l'inventaire");
            System.out.println("2. Gestion des ventes");
            System.out.println("3. Gestion des clients");
            System.out.println("4. Rapports et analyses");
            System.out.println("5. Quitter");

            String entry = scanner.nextLine();

            switch (entry) {
                case "1":
                    ihmArticle.start();
                    break;
                case "2":
                    ihmVente.start();
                    break;
                case "3":
                    ihmClient.start();
                    break;
                case "4":
                    ihmAnalyse.start();
                    break;
                case "5":
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
}
