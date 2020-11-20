package com.payMayBuddy.payMayBuddy.model;

import java.sql.Date;
import java.time.LocalDate;

public class Utilisateur {

    private int id_user;
    private String prenom;
    private String nom;
    private String email;
    private String tel;
    private String mot_de_passe;

    public Utilisateur() {
    }

    public Utilisateur(int id_user, String prenom, String nom, String email, String tel, String mot_de_passe) {
        this.id_user = id_user;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.tel = tel;
        this.mot_de_passe = mot_de_passe;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
