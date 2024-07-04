package org.example.controller;

import org.example.entity.Client;
import org.example.service.ClientService;
import org.example.entity.Vente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IHMClient {

    private final ClientService clientService;
    private final Scanner scanner;

    public IHMClient(Scanner scanner) {
        this.scanner = scanner;
        this.clientService = new ClientService();
    }

    public void start() {
        while (true) {
            System.out.println("---- Gestion des clients ----");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Modifier un client");
            System.out.println("3. Supprimer un client");
            System.out.println("4. Consulter les clients");
            System.out.println("5. Retour");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    updateClient();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    viewClients();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void addClient() {
        System.out.println("Ajouter un client");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Age : ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        List<Vente> historiqueAchats = new ArrayList<>();

        Client client = new Client(nom, prenom, age, email, historiqueAchats);
        clientService.addClient(client);

        System.out.println("Client ajouté avec succès !");
    }

    private void updateClient() {
        System.out.println("Modifier un client");
        System.out.print("ID du client à modifier : ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Client client = clientService.findById(id);
        if (client == null) {
            System.out.println("Client non trouvé.");
            return;
        }

        System.out.print("Nouveau nom (" + client.getNom() + ") : ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) {
            client.setNom(nom);
        }

        System.out.print("Nouveau prénom (" + client.getPrenom() + ") : ");
        String prenom = scanner.nextLine();
        if (!prenom.isEmpty()) {
            client.setPrenom(prenom);
        }

        System.out.print("Nouvel âge (" + client.getAge() + ") : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        if (age > 0) {
            client.setAge(age);
        }

        System.out.print("Nouvel email (" + client.getEmail() + ") : ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            client.setEmail(email);
        }

        clientService.updateClient(client);
        System.out.println("Client modifié avec succès !");
    }

    private void deleteClient() {
        System.out.println("Supprimer un client");
        System.out.print("ID du client à supprimer : ");
        long id = scanner.nextLong();
        scanner.nextLine();

        clientService.deleteClient(id);
        System.out.println("Client supprimé avec succès !");
    }

    private void viewClients() {
        System.out.println("Consulter les clients");
        clientService.getAllClients().forEach(System.out::println);
    }
}
