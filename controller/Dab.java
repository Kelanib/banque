package com.afpa.banque.controller;

import com.afpa.banque.objects.Compte;
import sun.jvm.hotspot.opto.HaltNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dab {

    // variables membres
    private ArrayList<Compte> comptes;

    // Constructor Dab avec une liste de comptes
    public Dab(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }

    // constructor par defaut
    public Dab() {
        comptes = new ArrayList<>();

        // Ajout de comptes
        ajouterCompte(new Compte("34567", 3000.00, 500.00));
        ajouterCompte(new Compte("34568", 2000.00, 300.00));
        ajouterCompte(new Compte("34569", 1000.00, 100.00));
        ajouterCompte(new Compte("34570", 2750.00, 340.00));
        ajouterCompte(new Compte("34571", 5000.00, 650.00));
    }

    public ArrayList<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }

    public void activer(Compte compteClient) throws IOException {

        String choixUtilisateur;

        System.out.printf("%nFaites votre choix d'opération%n" +
                "\t1 -> Retrait%n" +
                "\t2 -> Dépot%n" +
                "\t3 -> Solde%n" +
                "\t4 -> Quitter%n");


        choixUtilisateur = validerChoixUtilisateur(inputFromUser());

        System.out.printf("Vous avez fait le choix %s%n", choixUtilisateur);

        switch (choixUtilisateur) {

            case "1":
                double montantRetrait;

                System.out.print("Saisissez le montant à retirer: ");

                // Check montant en tant qu'entier naturel
                montantRetrait = validerMontant(inputFromUser());

                // procede au retrait et affiche la situation du compte
                System.out.println(compteClient.retirer(montantRetrait));

                break;

            case "2":
                double montantDepot;

                System.out.print("Saisissez le montant à déposer: ");

                // Check montant en tant qu'entier naturel
                montantDepot = validerMontant(inputFromUser());

                // Procede au depot et affiche la situation du compte
                System.out.println(compteClient.deposer(montantDepot));

                break;

            case "3":
                System.out.println("Situation actuelle de votre compte");
                System.out.println(compteClient.toString());
                break;

            case "4":

                break;

            case "5":
                System.out.printf("L'application s'arrête!");;
        }  // end switch

    } // end method activer()

    // Method inputFromUser() gets input from the user and returns it as a String
    public String inputFromUser() throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

        return bufferedReader.readLine();
    } // end method  inputFromUser

    // Methode validerChoixUtilisateur()
    public String validerChoixUtilisateur(String userChoice) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while (!(userChoice.matches("[1-5]"))) {
            System.out.println("Vous devez saisir un choix valide!");
            System.out.printf("Entrer:%n\t" +
                    "1 -> Retrait%n\t" +
                    "2 -> Dépot%n\t" +
                    "3 -> Solde%n\t" +
                    "4 -> Faire un virement%n\t" +
                    "5 -> Quitter%n");

            userChoice = bufferedReader.readLine();
        }

        return userChoice;
    } // end methode validerChoixUtilisateur

    // Methode validerMontant teste la validite du montant saisi par l'utilisateur (Check entier naturel)
    private double validerMontant(String montant) throws IOException {

        while (!(montant.matches("^[1-9]|\\d{2,}"))) {
            System.out.print("Le montant saisi contient une erreur, entrer un nombre entier comme montant: ");

            montant = inputFromUser();
        }

        return Double.parseDouble(montant);
    } // end method ValiderMontant()

    // methode AjouterUnCompte()
    public void ajouterCompte(final Compte compte) {
        getComptes().add(compte);
    }

    // methode pour supprimer un compte
    public void supprimerCompte(final Compte compte) {
        getComptes().remove(compte);
    }

    // methode selectionnerCompteCourant()
    public void selectionnerCompteCourant() throws IOException {
        System.out.print("Selectionnez un compte courant [1.." + getComptes().size() + "]: ");

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String userResponse = bufferedReader.readLine();
        int numeroCompte;
        String alert = "Le compte specifié n'existe pas, le programme s'arrete!";

        // Check whether userResponse is a parsabale integer (not containing charcater
        try {
            numeroCompte = Integer.parseInt(userResponse);

            if ( numeroCompte >= 1 && numeroCompte <= getComptes().size()) {
                // test
                System.out.printf("%nInformation de votre compte bancaire:%n");
                System.out.println(getComptes().get(Integer.parseInt(userResponse)- 1));
                activer(getComptes().get(Integer.parseInt(userResponse) - 1));
            } else {
                System.out.print(alert);
            }
        } catch (NumberFormatException nfe) {
            System.out.printf(alert + "%n" + nfe.getMessage());
        }
    } // end method selectionnerCompteCourant()
} // end class Dab
