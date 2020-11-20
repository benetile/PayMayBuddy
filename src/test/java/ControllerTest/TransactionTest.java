package ControllerTest;

import com.payMayBuddy.payMayBuddy.Service.Dao.CompteDao;
import com.payMayBuddy.payMayBuddy.Service.Dao.TransactionDao;
import com.payMayBuddy.payMayBuddy.Service.Dao.UtilisateurDao;
import com.payMayBuddy.payMayBuddy.controller.TransactionController;
import com.payMayBuddy.payMayBuddy.model.Compte;
import com.payMayBuddy.payMayBuddy.model.Transaction;
import com.payMayBuddy.payMayBuddy.model.Utilisateur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TransactionDao.class, UtilisateurDao.class, CompteDao.class, TransactionController.class})
public class TransactionTest {
    @MockBean
    TransactionDao transactionDao;
    @MockBean
    UtilisateurDao utilisateurDao;
    @MockBean
    CompteDao compteDao;

    @Autowired
    TransactionController transactionController;

    Utilisateur utilisateur = new Utilisateur(1, "Benny", "Etilefanela", "etilebenny@gmail.com", "078962852", "benny");

    Transaction transaction = new Transaction(1,"test unitaire","etilebenny@gmail.com",Date.valueOf(LocalDate.now()), BigDecimal.valueOf(20),1,1);
    Compte compte = new Compte(1,"gg",BigDecimal.valueOf(100),1);
    Compte debit = new Compte(1,"ff",BigDecimal.valueOf(500),1);
    Compte credit = new Compte(2," ",BigDecimal.valueOf(200),2);
    List<Transaction> transactionList = new ArrayList<>();


    @Test
    public void addEmailTest() throws SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(1);

        String result = transactionController.AddEmailForTransaction(utilisateur, request);
        when(utilisateurDao.findUtilisateurByEmail(anyString())).thenReturn(utilisateur);
        assertThat(result).isEqualTo("transaction");

    }
    @Test
    public void fundAnAccountTest()throws SQLException {


        Transaction tra = new Transaction();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(1);
        when(utilisateurDao.findUtilisateurByEmail(anyString())).thenReturn(utilisateur);


    }


}
