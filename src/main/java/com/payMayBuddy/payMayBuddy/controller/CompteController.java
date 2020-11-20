package com.payMayBuddy.payMayBuddy.controller;

import com.payMayBuddy.payMayBuddy.Service.Dao.CompteDao;
import com.payMayBuddy.payMayBuddy.Service.Dao.UtilisateurDao;
import com.payMayBuddy.payMayBuddy.model.Compte;
import com.payMayBuddy.payMayBuddy.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;

@Controller
public class CompteController {

    @Autowired
    private CompteDao compteDao;
    @Autowired
    private UtilisateurDao utilisateurDao;

  //  Utilisateur user;
    //Compte newCompte = new Compte();

    @PostMapping("/creerCompte")
    public String createAnCompte(@ModelAttribute("newCompte") Compte newCompte, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int id= (int) session.getAttribute("id");
        newCompte.setUser_id(id);
        newCompte.setSold(BigDecimal.ZERO);
        Compte verif= compteDao.saveCompte(newCompte);
        request.setAttribute("mode","CREATION");
        return "creerCompte";
    }
    @GetMapping("/creerCompte")
    public String getComptenewCompte(Model model){
        model.addAttribute("newCompte",new Compte());
        return "creerCompte";
    }
    @GetMapping("/compte")
    public String displayCompteUser(Model model, WebRequest webRequest, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) webRequest.getAttribute("userSession", WebRequest.SCOPE_SESSION);

        int id = (int) session.getAttribute("id");
        Compte compteUser = compteDao.findByIdUser(id);
        if (compteUser != null) {
            model.addAttribute("compteUser", compteUser);
            request.setAttribute("mode", "INFO_COMPTE");
            return "compte";
        } else {

            request.setAttribute("mode", "CREATED_COMPTE");
            return "compte";
        }
    }

    @PostMapping("/compte")
    public String fundMyAccount(@ModelAttribute("compte") Compte compte, HttpServletRequest request) throws SQLException {

        HttpSession session = request.getSession();
        int id= (int) session.getAttribute("id");

        Compte compteUpdate=compteDao.findByIdUser(id);
        BigDecimal amount = compteUpdate.getSold();
        compteUpdate.setSold(compte.getSold().add(amount));
        compteDao.supplyCompte(id,compteUpdate.getSold());

        request.setAttribute("mode","APPROVISIONNER");
        return "compte";
    }
    @PostMapping("/virement")
    public String fareVirement(@ModelAttribute("compte") Compte compte,HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int id= (int) session.getAttribute("id");

        Compte virementCompte = compteDao.findByIdUser(id);
        BigDecimal amount = virementCompte.getSold();
        if(virementCompte.getSold().compareTo(compte.getSold())>0) {
            virementCompte.setSold(amount.subtract(compte.getSold()));
            compteDao.debitCompte(id, virementCompte.getSold());
            request.setAttribute("mode", "SUCCES");
            return "virement";
        }else
        {
            request.setAttribute("mode","ECHEC_OPERATION");
            return "virement";
        }
    }


    @GetMapping("/supprimer-compte")
    public String deletecompte(@RequestParam int id_compte, HttpServletRequest request) throws SQLException {
        compteDao.deleteCompte(id_compte);
        request.setAttribute("mode","SUPPRIMER_COMPTE");
        return "compte";
    }


        @GetMapping("/virement")
        public String displayCompte(Model model, WebRequest webRequest, HttpServletRequest request) throws SQLException {
            HttpSession session = request.getSession();
            Utilisateur user =(Utilisateur) webRequest.getAttribute("userSession",WebRequest.SCOPE_SESSION);

            int id = (int) session.getAttribute("id");
            Compte compteUser=compteDao.findByIdUser(id);
            if(compteUser!=null) {
                model.addAttribute("compteV", compteUser);
                request.setAttribute("mode", "VIREMENT");
                return "virement";
            }else {

                request.setAttribute("mode", "CREATED_COMPTE");
                return "compte";
            }
    }
}
