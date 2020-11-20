package com.payMayBuddy.payMayBuddy.model;

public class Beneficiaire {
    private int id_beneficiaire;
    private String email;
    private int id_utilisateur;

    public Beneficiaire() {
    }

    public Beneficiaire(int id_beneficiaire, String email, int id_utilisateur) {
        this.id_beneficiaire = id_beneficiaire;
        this.email = email;
        this.id_utilisateur = id_utilisateur;
    }


    public int getId_beneficiaire() {
        return id_beneficiaire;
    }

    public void setId_beneficiaire(int id_beneficiaire) {
        this.id_beneficiaire = id_beneficiaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

}
