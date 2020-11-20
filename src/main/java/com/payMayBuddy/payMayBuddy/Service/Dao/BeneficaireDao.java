package com.payMayBuddy.payMayBuddy.Service.Dao;

import com.payMayBuddy.payMayBuddy.model.Beneficiaire;

import java.sql.SQLException;
import java.util.List;

public interface BeneficaireDao {

    Beneficiaire saveBeneficiare(Beneficiaire beneficiaire)throws SQLException;

    List<Beneficiaire> findAllBeneficiairesByUser(int id_user)throws SQLException;

    Beneficiaire deleteBeneficiaire(int id)throws SQLException;

    Beneficiaire updateBeneficiaire(int id,Beneficiaire update)throws SQLException;

}
