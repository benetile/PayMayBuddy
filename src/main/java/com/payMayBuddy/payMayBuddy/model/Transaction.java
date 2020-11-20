package com.payMayBuddy.payMayBuddy.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Transaction {

    private int id_transaction;
    private String description;
    private String email_user;
    private Date date;
    private BigDecimal montant;
    private int user_id;
    private int compte_id;

    public Transaction() {
    }

    public Transaction(int id_transaction, String description, String email_user, Date date, BigDecimal montant, int user_id, int compte_id) {
        this.id_transaction = id_transaction;
        this.description = description;
        this.email_user = email_user;
        this.date = date;
        this.montant = montant;
        this.user_id = user_id;
        this.compte_id = compte_id;
    }


    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCompte_id() {
        return compte_id;
    }

    public void setCompte_id(int compte_id) {
        this.compte_id = compte_id;
    }
}
