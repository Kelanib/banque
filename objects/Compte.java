package com.afpa.banque.objects;

public class Compte {
    private String numeroCompte;
    private double solde;
    private double decouvert = 0.00;

    public Compte() {
    }

    public Compte(String numeroCompte, double solde, double decouvert) {
        setNumeroCompte(numeroCompte);
        setSolde(solde);
        setDecouvert(decouvert);
    }

    // creation des getters et setters
    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        if (numeroCompte != null) {
            this.numeroCompte = numeroCompte;
        } else {
            this.setNumeroCompte("000000000000");
        }
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {

        if (solde != 0.00) {
            this.solde = solde;
        }
        else
            this.solde = 0.00;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    // Methode deposer() permet de déposer une somme qur le compte
    public String deposer(double somme) {
        this.solde = this.getSolde() + somme;

        // Affiche l'etat du compte apres depot
        return String.format("%nSituation de votre compte apres depot de %.2f Euros%n%s",
                somme,
                this.toString());
    } // Fin methode deposer

    // Methode retirer() permet de retirer une somme sur le compte
    public String retirer(double somme) {
        String situationCompte;

        if (this.getSolde() + this.getDecouvert() >= somme) {
            this.solde -= somme;

            // J'affiche le nouveau solde apres retrait
            situationCompte =  String.format(
                    "%nSituation de votre compte apres retrait de %.2f Euros%n%s",
                    somme,
                    this.toString());
        } else {
            situationCompte = String.format("%nRetrait de %.2f non autorisé, la transaction va s'arrêter!", somme);
        }

        return situationCompte;
    } // Fin methode retirer()


    @Override // method toString de la classe Object orridé dans class Compte
    public String toString() {
        return String.format("\tNumero Compte: '" + numeroCompte + '\'' +
                "\n\t%15s %.2f Euros", "Solde: ", solde);
    }

}
