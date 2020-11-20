package com.payMayBuddy.payMayBuddy.Service.Impl;

import com.payMayBuddy.payMayBuddy.Service.Dao.UtilisateurDao;
import com.payMayBuddy.payMayBuddy.application.ConfigDB;
import com.payMayBuddy.payMayBuddy.model.Utilisateur;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UtilisateurDaoImpl implements UtilisateurDao {

    @Autowired
    private ConfigDB configDB;

    public static Logger logger = Logger.getLogger(UtilisateurDaoImpl.class);

    private Connection connection = null;

    @Override
    public List<Utilisateur> findAllUtilisateurs()throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM utilisateur;");
        ResultSet rs = stmt.executeQuery();
        List<Utilisateur> utilisateurs = new ArrayList<>();

        while(rs.next()){
            Utilisateur user = new Utilisateur();
            user.setPrenom(rs.getString("prenom"));
            user.setNom(rs.getString("nom"));
            user.setTel(rs.getString("tel"));
            user.setEmail(rs.getString("email"));
            user.setMot_de_passe(rs.getString("mot_de_passe"));
            utilisateurs.add(user);
        }
        return utilisateurs;
    }

    @Override
    public Utilisateur findUtilisateurById(int id_user) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement  stmt = connection.prepareStatement("SELECT * from utilisateur WHERE id_utilisateur =?;");
        stmt.setInt(1, id_user);

        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId_user(result.getInt("id_utilisateur"));
            utilisateur.setPrenom(result.getString("prenom"));
            utilisateur.setNom(result.getString("nom"));
            utilisateur.setTel(result.getString("tel"));
            utilisateur.setEmail(result.getString("email"));
            utilisateur.setMot_de_passe(result.getString("mot_de_passe"));

            return utilisateur;
        }
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateurByFirstName(String firstName) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement  stmt = connection.prepareStatement("SELECT * from utilisateur WHERE firstname LIKE '%?%';");
        stmt.setString(1, firstName);

        ResultSet results = stmt.executeQuery();

        List<Utilisateur> match = new ArrayList<>();
        while (results.next()){
            Utilisateur user = new Utilisateur();
            user.setId_user(results.getInt("id_utilisateur"));
            user.setPrenom(results.getString("prenom"));
            user.setNom(results.getString("nom"));
            user.setTel(results.getString("tel"));
            user.setEmail(results.getString("email"));
            user.setMot_de_passe(results.getString("mot_de_passe"));

            match.add(user);
        }
        results.close();
        return match;
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String email) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement pstmt = connection.prepareCall("SELECT id_utilisateur,email,prenom,nom FROM utilisateur WHERE email=?;");
        pstmt.setString(1,email);

        ResultSet results = pstmt.executeQuery();

        while (results.next()) {
            Utilisateur user = new Utilisateur();
            user.setId_user(results.getInt("id_utilisateur"));
            user.setEmail(results.getString("email"));
            user.setPrenom(results.getString("prenom"));
            user.setNom(results.getString("nom"));
            return user;
        }
        return null;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO utilisateur (prenom,nom,tel,email,mot_de_passe) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1,user.getPrenom());
        stmt.setString(2,user.getNom());
        stmt.setString(3, user.getTel());
        stmt.setString(4, user.getEmail());
        stmt.setString(5, user.getMot_de_passe());

        stmt.executeUpdate();
        ResultSet results = stmt.getGeneratedKeys();
        if (results.next()){
            user.setId_user(results.getInt(1));
        }
        return user;
    }

    @Override
    public Utilisateur getEmailAndPasswordForUser(String email, String mdp) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id_utilisateur,prenom,email,mot_de_passe FROM utilisateur WHERE email=? AND mot_de_passe=?;");

        stmt.setString(1,email);
        stmt.setString(2,mdp);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){

            Utilisateur ut = new Utilisateur();
            ut.setId_user(rs.getInt("id_utilisateur"));
            ut.setPrenom(rs.getString("prenom"));
            ut.setEmail(rs.getString("email"));
            ut.setMot_de_passe(rs.getString("mot_de_passe"));

            System.out.println(ut.getPrenom());

            return ut;
        }
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(int id, Utilisateur update)throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE utilisateur SET prenom=?,nom=?,tel=?,mot_de_passe=? WHERE id_utilisateur=?;");

        stmt.setInt(5,id);
       stmt.setString(1,update.getPrenom());
       stmt.setString(2,update.getNom());
       stmt.setString(3, update.getTel());
       stmt.setString(4, update.getMot_de_passe());

       stmt.executeUpdate();
       stmt.close();
       return update;
    }

    @Override
    public Utilisateur updateUser(int id_user) throws SQLException {
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE utilisateur SET prenom=?,nom=?,tel=?,mot_de_passe=? WHERE id_utilisateur=?;");

        Utilisateur update = new Utilisateur();
        stmt.setInt(5,id_user);
        stmt.setString(1,update.getPrenom());
        stmt.setString(2,update.getNom());
        stmt.setString(3, update.getTel());
        stmt.setString(4, update.getMot_de_passe());

        stmt.executeUpdate();
        stmt.close();

        return update;
    }

    @Override
    public Utilisateur deleteUtilisateur(int user_Delete)throws SQLException{
        connection= configDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur=?;");
        stmt.setInt(1,user_Delete);
        stmt.executeUpdate();
        stmt.close();
        return null;
    }
}
