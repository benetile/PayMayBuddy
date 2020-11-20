package com.payMayBuddy.payMayBuddy.Service.Impl;

import com.payMayBuddy.payMayBuddy.Service.Dao.BeneficaireDao;
import com.payMayBuddy.payMayBuddy.application.ConfigDB;
import com.payMayBuddy.payMayBuddy.model.Beneficiaire;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BeneficiaireDaoImpl implements BeneficaireDao {
    @Autowired
    private ConfigDB configDB;

    public static Logger logger = Logger.getLogger(UtilisateurDaoImpl.class);

    private Connection connection = null;

    @Override
    public Beneficiaire saveBeneficiare(Beneficiaire beneficiaire) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO beneficiaire (id_utilisateur,email) VALUES(?,(SELECT utilisateur.email FROM utilisateur WHERE utilisateur.email=?))", Statement.RETURN_GENERATED_KEYS);

        pstmt.setInt(1,beneficiaire.getId_utilisateur());
        pstmt.setString(2,beneficiaire.getEmail());

        pstmt.executeUpdate();
        ResultSet results = pstmt.getGeneratedKeys();
        if (results.next()){
            beneficiaire.setId_beneficiaire(results.getInt(1));
        }
        return beneficiaire;
    }

    @Override
    public List<Beneficiaire> findAllBeneficiairesByUser(int id_user) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM beneficiaire WHERE id_utilisateur=?;");
        pstmt.setInt(1, id_user);
        List<Beneficiaire>beneficiaires = new ArrayList<>();

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            Beneficiaire beneficiaire = new Beneficiaire();
            beneficiaire.setId_beneficiaire(rs.getInt("id_beneficiaire"));
            beneficiaire.setEmail(rs.getString("email"));
            beneficiaire.setId_utilisateur(rs.getInt("id_utilisateur"));

            beneficiaires.add(beneficiaire);
        }
        return beneficiaires;
    }

    @Override
    public Beneficiaire deleteBeneficiaire(int id) throws SQLException {
        return null;
    }

    @Override
    public Beneficiaire updateBeneficiaire(int id, Beneficiaire update) throws SQLException {
        return null;
    }
}
