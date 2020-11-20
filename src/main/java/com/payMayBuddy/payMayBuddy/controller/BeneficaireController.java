package com.payMayBuddy.payMayBuddy.controller;

import com.payMayBuddy.payMayBuddy.Service.Dao.BeneficaireDao;
import com.payMayBuddy.payMayBuddy.Service.Dao.UtilisateurDao;
import com.payMayBuddy.payMayBuddy.model.Beneficiaire;
import com.payMayBuddy.payMayBuddy.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class BeneficaireController {
    @Autowired
    UtilisateurDao utilisateurDao;
    @Autowired
    BeneficaireDao beneficaireDao;

    @PostMapping("/beneficiaire")
    public String addNewBeneficiaire(@ModelAttribute("email") Utilisateur user, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        Utilisateur utilisateur = new Utilisateur();
        Beneficiaire benef = new Beneficiaire();
        utilisateur = utilisateurDao.findUtilisateurByEmail(user.getEmail());
        benef.setId_utilisateur(id);
        benef.setEmail(utilisateur.getEmail());
        if (utilisateur != null) {
            beneficaireDao.saveBeneficiare(benef);
            request.setAttribute("mode","SUCCES");
            request.setAttribute("user", utilisateur);
            return "beneficiaire";
        } else if (utilisateur == null) {
            request.setAttribute("mode","ECHEC");
            return "beneficiaire";
        }
        return "beneficiaire";
    }

    public  String displayAllBeneficiaire(HttpServletRequest request)throws SQLException{
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        List<Beneficiaire> beneficiaires = beneficaireDao.findAllBeneficiairesByUser(id);
        request.setAttribute("transactions", beneficiaires);
        return "";
    }
}
