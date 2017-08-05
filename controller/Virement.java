package com.afpa.banque.controller;

import com.afpa.banque.objects.Compte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatConversionException;

public class Virement {

    private Dab dab;
    private int[] indexComptesVirement; // stores
    private double somme; // somme à virer

    // contstuctor
    public Virement(Dab dab, int[] indexComptesVirement) {
        this.dab = dab;
        this.indexComptesVirement = indexComptesVirement;
    }

    public Virement() {
        int nombreComptes = 2;
        this.dab = new Dab();
        this.indexComptesVirement = new int[nombreComptes];
        this.setSomme();
    }

    // getters et setters
    public Dab getDab() {
        return dab;
    }

    public int[] getIndexComptesVirement() {
        return indexComptesVirement;
    }

    public void setDab(Dab dab) {
        this.dab = dab;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme() { // set the amount of money for the transfert
        double somme;
        String alert = "\nLe montant de transfert saisi n'est pas autorisé. Transaction annulée!";

        System.out.print("\nEntrer le montant du transfert: ");

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            somme = Double.parseDouble(bufferedReader.readLine());

            if (somme >= 0) {
                this.somme = somme;
            } else {
                System.out.println("\nLe montant de virement doit etre positif");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println(alert + "\n\t" + e.getMessage());
            System.exit(1);
        }
    }

    public void setIndexComptesVirement(int[] indexComptesVirement) {
        this.indexComptesVirement = indexComptesVirement;
    }

    // Virement somme d'un compte a un autre!
    public void virerSomme(Compte compteSource, Compte compteDestinataire, double sommeVirement) {

        if (sommeVirement > 0) {
            if ((compteSource.getSolde() + compteSource.getDecouvert()) >= sommeVirement) {
                compteDestinataire.deposer(sommeVirement); // virement de la somme sur le compte destinataire
                compteSource.retirer(sommeVirement); // Mise a jour compte source
            } else {
                System.out.printf(
                        "Votre avoir n'est pas suffisant pour un virement de %.2f Euros. La transaction est annulée!",
                        sommeVirement);
            }
        } else {
            System.out.println("Le montant de virement doit etre positif. L'operation est annulée!");
        }// end else

    } // end methode virerSomme

    // get indices des comptes
    public int[] returnIndexComptesVirement() {
        int[] indexComptesVirement = new int[2];
        int indexCompteSource;
        int indexCompteDestinataire;
        String alert = "Le(s) numero(s) de compte(s) saisi(s) est (sont) incorrect(s). Opération refusée!";

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {

            System.out.print("\nVeuillez entrer le numero du compte à débiter [1.." + dab.getComptes().size() + "]: ");
            indexCompteSource = Integer.parseInt(bufferedReader.readLine());

            System.out.print("\nVeuillez entrer le numero du compte à créditer [1.." + dab.getComptes().size() + "]: ");
            indexCompteDestinataire = Integer.parseInt(bufferedReader.readLine());

            // Test des index entrés par l'utilisateur corresepondants aux numeros de comptes réels
            if (((indexCompteSource >= 1) && (indexCompteSource <= dab.getComptes().size())) &&
                    ((indexCompteDestinataire >= 1) && (indexCompteDestinataire <= dab.getComptes().size()))) {

                if (indexCompteSource == indexCompteDestinataire) {
                    System.out.println("\nVous ne pouvez pas réaliser de virement sur un même compte. Opération réfusée!");
                } else {
                    indexComptesVirement[0] = indexCompteSource - 1;
                    indexComptesVirement[1] = indexCompteDestinataire - 1;
                }
            } else {
                System.out.println("\nLes numeros de comptes saisis sont incorrects. Opération refusée!");
            } // end outer else

        } catch (NumberFormatException | IllegalFormatConversionException | IOException exc) {
            System.out.println("\n" + alert + "\n\t" + exc.getMessage());
            System.exit(1);
        } // end catch

        return indexComptesVirement;
    }
}
