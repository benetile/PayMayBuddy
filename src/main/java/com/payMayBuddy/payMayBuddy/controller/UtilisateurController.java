package com.payMayBuddy.payMayBuddy.controller;

import com.payMayBuddy.payMayBuddy.Service.Dao.UtilisateurDao;
import com.payMayBuddy.payMayBuddy.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@SessionAttributes("utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @ModelAttribute("utilisateur")
    public Utilisateur utilisateur() {
        return new Utilisateur();
    }

    @PostMapping("/login")
    public String identifyUser(@ModelAttribute("utilisateur") Utilisateur utilisateur, HttpServletRequest request) throws SQLException {
        Utilisateur user = utilisateurDao.getEmailAndPasswordForUser(utilisateur.getEmail(), utilisateur.getMot_de_passe());
        HttpSession session = request.getSession();

        if (user != null) {
            session.setAttribute("prenom", user.getPrenom());
            session.setAttribute("id", user.getId_user());
            return "index";
        } else if (null == null) {
            request.setAttribute("error", "Invalid email or password");
            return "login";
        }
        return null;
    }

    @GetMapping("/login")
    public String loginPage(Utilisateur utilisateur, Model model) throws Exception {
        model.addAttribute("utilisateur", utilisateur);
        return "login";
    }

    @PostMapping("/registrer")
    public String registrerUser(@ModelAttribute("utilisateur") Utilisateur user, HttpServletRequest request) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur = utilisateurDao.findUtilisateurByEmail(user.getEmail());
        if (utilisateur == null) {
            utilisateurDao.createUtilisateur(user);
            return "login";
        } else {
            //request.setAttribute("error","Email existe déjà");
            return "registrer";
        }

    }

    @GetMapping("/registrer")
    public String pageRegistrer(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "registrer";
    }

    @GetMapping("/logout")
    public String disconnect(WebRequest request) {
        request.removeAttribute("userSession", WebRequest.SCOPE_SESSION);
        return "login";
    }

    public String verifyEmail(Model model, Utilisateur user) throws SQLException {
        Utilisateur utilisateur = utilisateurDao.findUtilisateurByEmail(user.getEmail());
        model.addAttribute("email", utilisateur.getEmail());
        return "transaction";
    }

    @GetMapping("/profil")
    public String afficherUtilisateur(Model model, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        Utilisateur utilisateur = utilisateurDao.findUtilisateurById(id);
        if (utilisateur.getId_user() == id) {
            model.addAttribute("utilisateur", utilisateur);
            request.setAttribute("mode", "PROFIL");
            return "profil";
        } else {
            return null;
        }
    }

    @PostMapping("/upadteUser")
    public String modifierInfoUser(@ModelAttribute("utilisateur") Utilisateur update, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        Utilisateur utilisateur = utilisateurDao.findUtilisateurById(id);
        request.setAttribute("update", utilisateurDao.updateUtilisateur(id, update));
        request.setAttribute("mode", "UPDATE_USER");
        return "upadteUser";

    }
    @GetMapping("/deleteUser")
    public String supprimerUtilisateurr(@RequestParam int id_user, HttpServletRequest request) throws SQLException {
        utilisateurDao.deleteUtilisateur(id_user);
        request.setAttribute("mode", "SUPPRIMER_USER");
        return "profil";
    }
}
