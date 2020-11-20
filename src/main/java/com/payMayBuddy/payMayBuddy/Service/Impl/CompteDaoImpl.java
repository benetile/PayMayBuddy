package com.payMayBuddy.payMayBuddy.Service.Impl;

import com.payMayBuddy.payMayBuddy.Service.Dao.CompteDao;
import com.payMayBuddy.payMayBuddy.application.ConfigDB;
import com.payMayBuddy.payMayBuddy.model.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompteDaoImpl implements CompteDao {

    @Autowired
    private ConfigDB configDB;

    private Connection connection;
    @Override
    public List<Compte> findAllCompte() throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM compte;");
        ResultSet rs = stmt.executeQuery();
        List<Compte> comptes = new ArrayList<>();

        while(rs.next()){
            Compte compte = new Compte();
            compte.setId_compte(rs.getInt("id_compte"));
            compte.setSold(rs.getBigDecimal("sold"));
            compte.setUser_id(rs.getInt("id_utilisateur"));

            comptes.add(compte);
        }
        return comptes;
    }

    @Override
    public Compte findById(int id) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM compte WHERE id_compte=?;");
        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        Compte compteId = new Compte();
        while (rs.next()){
            compteId.setId_compte(rs.getInt("id_compte"));
            compteId.setIban(rs.getString("iban"));
            compteId.setSold(rs.getBigDecimal("sold"));
            compteId.setUser_id(rs.getInt("id_utilisateur"));
            return compteId;
        }
    return null;
    }

    @Override
    public Compte findByIdUser(int id_user) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM compte WHERE id_utilisateur=?;");
        pstmt.setInt(1,id_user);
        ResultSet rs = pstmt.executeQuery();

        Compte compteId_User = new Compte();
        while (rs.next()){
            compteId_User.setId_compte(rs.getInt("id_compte"));
            compteId_User.setIban(rs.getString("iban"));
            compteId_User.setSold(rs.getBigDecimal("sold"));
            compteId_User.setUser_id(rs.getInt("id_utilisateur"));
            return compteId_User;
        }
        return null;
    }

    @Override
    public Compte saveCompte(Compte compte) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO compte (iban,sold,id_utilisateur) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1,compte.getIban());
        pstmt.setBigDecimal(2,compte.getSold());
        pstmt.setInt(3,compte.getUser_id());

        pstmt.executeUpdate();
        ResultSet results = pstmt.getGeneratedKeys();
        if (results.next()){
            compte.setId_compte(results.getInt(1));
        }
        return compte;
    }

    @Override
    public Compte supplyCompte(int id_user, BigDecimal sold) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE compte SET sold=? WHERE id_utilisateur=?;");
        pstmt.setBigDecimal(1,sold);
        pstmt.setInt(2,id_user);
        pstmt.executeUpdate();
        pstmt.close();

        return null;
    }

    @Override
    public void debitCompte(int id_compte,BigDecimal sold) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE compte SET sold=? WHERE id_compte=?;");
        pstmt.setBigDecimal(1,sold);
        pstmt.setInt(2,id_compte);

        pstmt.executeUpdate();
    }

    @Override
    public void updateCompte(Compte updateCompte) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE compte SET sold=? WHERE id_user=?;");

        pstmt.setBigDecimal(1,updateCompte.getSold());

        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public Compte deleteCompte(int id_compte) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM compte WHERE id_compte=?");
        pstmt.setInt(1,id_compte);
        pstmt.executeUpdate();
        pstmt.close();
        return null;
    }
}
