package com.payMayBuddy.payMayBuddy.Service.Dao;

import com.payMayBuddy.payMayBuddy.model.Compte;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface CompteDao {
    List<Compte> findAllCompte() throws SQLException;

    Compte findById(int id) throws SQLException;

    Compte findByIdUser(int id)throws  SQLException;

    Compte saveCompte(Compte compte) throws SQLException;

    void debitCompte(int id_user, BigDecimal sold)throws SQLException;

    Compte supplyCompte(int id_user, BigDecimal sold) throws SQLException;

    void updateCompte(Compte updateCompte) throws SQLException;

    Compte deleteCompte(int id_compte) throws SQLException;

}
