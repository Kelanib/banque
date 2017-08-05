package com.afpa.banque.View;

import com.afpa.banque.controller.Virement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class VirementView {
    public static void main(String[] args) {

        System.out.println("Bonjour! Ce service vous permet de réaliser des transfert d'un compte vers un autre.");

        Virement newVirement = new Virement();

        // recupere les numeros des comptes de transfert (Compte à debiter et Compte à créditer)
        newVirement.setIndexComptesVirement(newVirement.returnIndexComptesVirement()) ;

        System.out.printf("%nSituation des deux comptes avant le virement d'un montant de %.2f", newVirement.getSomme());
        System.out.println("\n" + newVirement.getDab().getComptes().get(newVirement.getIndexComptesVirement()[0]));
        System.out.println("\n" + newVirement.getDab().getComptes().get(newVirement.getIndexComptesVirement()[1]));

        // Procede à l'operation de virement
        newVirement.virerSomme(
                newVirement.getDab().getComptes().get(newVirement.getIndexComptesVirement()[0]),
                newVirement.getDab().getComptes().get(newVirement.getIndexComptesVirement()[1]),
                newVirement.getSomme());

        System.out.printf("%nSituation des deux comptes après le virement d'un montant de %.2f", newVirement.getSomme());
        System.out.println("\n" + newVirement.getDab().getComptes().get(newVirement.getIndexComptesVirement()[0]));
        System.out.println("\n" + newVirement.getDab().getComptes().get(newVirement.getIndexComptesVirement()[1]));
    }
}
