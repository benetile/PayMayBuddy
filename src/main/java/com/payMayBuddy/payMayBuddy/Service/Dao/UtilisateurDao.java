package com.payMayBuddy.payMayBuddy.Service.Dao;

import com.payMayBuddy.payMayBuddy.model.Utilisateur;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface UtilisateurDao {

    List<Utilisateur> findAllUtilisateurs() throws SQLException;

    Utilisateur findUtilisateurById(int id_user) throws SQLException;

    List<Utilisateur> findUtilisateurByFirstName(String firstName)throws SQLException;

    Utilisateur findUtilisateurByEmail(String email)throws SQLException;

    Utilisateur createUtilisateur(Utilisateur user) throws SQLException;

    Utilisateur getEmailAndPasswordForUser(String email, String mdp)throws SQLException;

    Utilisateur updateUtilisateur(int id,Utilisateur update) throws SQLException;

    Utilisateur updateUser(int id_user)throws  SQLException;

    Utilisateur deleteUtilisateur(int user_Delete) throws SQLException;

}
