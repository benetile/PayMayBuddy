package com.payMayBuddy.payMayBuddy.model;

import java.math.BigDecimal;

public class Compte {

    private int id_compte;
    private String iban;
    private BigDecimal sold;
    private int user_id;

    public Compte() {
    }

    public Compte(int id_compte, String iban, BigDecimal sold, int user_id) {
        this.id_compte = id_compte;
        this.iban = iban;
        this.sold = sold;
        this.user_id = user_id;
    }

    public int getId_compte() {
        return id_compte;
    }

    public void setId_compte(int id_compte) {
        this.id_compte = id_compte;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getSold() {
        return sold;
    }

    public void setSold(BigDecimal sold) {
        this.sold = sold;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
