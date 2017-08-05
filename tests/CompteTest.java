package com.afpa.banque.tests;

import com.afpa.banque.objects.Compte;

public class CompteTest {
    public static void main(String[] args) {

        double montantDepot = 500.00;
        double montantRetrait = 2040.00;

        // instantiation de la classe Compte
        Compte monCompte = new Compte("121", 1500.00, 100);

        // Affichage des informations liées au compte
        System.out.println("Information de votre compte bancaire:");
        System.out.println(monCompte.toString());

        // Je fais un versement de montantDepot sur le compte
        System.out.println(monCompte.deposer(montantDepot));

        // Retrait de montantRetrait
        System.out.println(monCompte.retirer(montantRetrait));

    }
}
