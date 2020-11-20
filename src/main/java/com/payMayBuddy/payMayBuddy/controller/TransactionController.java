package com.payMayBuddy.payMayBuddy.controller;

import com.payMayBuddy.payMayBuddy.Service.Dao.BeneficaireDao;
import com.payMayBuddy.payMayBuddy.Service.Dao.CompteDao;
import com.payMayBuddy.payMayBuddy.Service.Dao.TransactionDao;
import com.payMayBuddy.payMayBuddy.Service.Dao.UtilisateurDao;
import com.payMayBuddy.payMayBuddy.model.Beneficiaire;
import com.payMayBuddy.payMayBuddy.model.Compte;
import com.payMayBuddy.payMayBuddy.model.Transaction;
import com.payMayBuddy.payMayBuddy.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    private UtilisateurDao utilisateurDao;
    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private CompteDao compteDao;
    @Autowired
    private BeneficaireDao beneficaireDao;

    @PostMapping("/approvisionner")
    public String fundAnAccount(@ModelAttribute("transaction") Transaction transaction, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        Compte compteDebit = compteDao.findByIdUser(id);
        Compte compteCredit = compteDao.findByIdUser(transaction.getUser_id());
        double tva = 0.005;

        transaction.setCompte_id(compteDebit.getId_compte());
        Date time = Date.valueOf(LocalDate.now());
        transaction.setDate(time);

        if (compteDebit.getSold().compareTo(transaction.getMontant()) > 0) {

            compteCredit.setSold(compteCredit.getSold().add(transaction.getMontant()));
            compteDao.supplyCompte(compteCredit.getUser_id(), compteCredit.getSold());
            compteDebit.setSold(compteDebit.getSold().subtract(transaction.getMontant()));
            compteDao.debitCompte(compteDebit.getId_compte(), compteDebit.getSold());
            transactionDao.createdTransaction(transaction);

            request.setAttribute("mode", "APPROVISIONNER_COMPTE");
            return "approvisionner";
        } else {
            request.setAttribute("mode", "ECHEC_TRANSACTION");
            return "approvisionner";
        }

    }

    @PostMapping("/transaction")
    public String AddEmailForTransaction(@ModelAttribute("email") Utilisateur user, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        Utilisateur utilisateur = new Utilisateur();
        Beneficiaire benef = new Beneficiaire();
        utilisateur = utilisateurDao.findUtilisateurByEmail(user.getEmail());
        benef.setId_utilisateur(id);
        benef.setEmail(utilisateur.getEmail());
        if (utilisateur != null) {
            beneficaireDao.saveBeneficiare(benef);
            request.setAttribute("user", utilisateur);
            return "approvisionner";
        } else if (utilisateur == null) {
            return "transaction";
        }
        return "transaction";
    }


    @GetMapping("/transaction")
    public String afficherTransactionByUser(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        Compte compte = compteDao.findByIdUser(id);
        List<Transaction> transactions = transactionDao.findTransactionByIdCompte(compte.getId_compte());
        request.setAttribute("mode", "index");
        request.setAttribute("transactions", transactions);
        return "transaction";
    }

}
