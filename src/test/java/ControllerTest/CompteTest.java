package ControllerTest;

import com.payMayBuddy.payMayBuddy.Service.Dao.CompteDao;
import com.payMayBuddy.payMayBuddy.Service.Dao.UtilisateurDao;
import com.payMayBuddy.payMayBuddy.controller.CompteController;
import com.payMayBuddy.payMayBuddy.model.Compte;
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
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CompteDao.class,UtilisateurDao.class, CompteController.class})
public class CompteTest {

    @MockBean
    CompteDao compteDao;
    @Autowired
    CompteController controller;
    @MockBean
    UtilisateurDao utilisateurDao;

    Compte compte = new Compte(1,"test", BigDecimal.valueOf(500),1);
    Utilisateur utilisateur = new Utilisateur(1, "Benny", "Etilefanela", "etilebenny@gmail.com", "078962852", "benny");

    @Test
    public void saveCompte()throws SQLException{
        when(utilisateurDao.findUtilisateurById(anyInt())).thenReturn(utilisateur);
        when(compteDao.saveCompte(compte)).thenReturn(compte);
        assertThat(compte).isNotNull();
    }
    @Test
    public void createCompte()throws SQLException{

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session =mock(HttpSession.class);

        when(request.getSession()).thenReturn( session);
        when(session.getAttribute("id")).thenReturn(1);
        String result = controller.createAnCompte(compte,request);
        when(utilisateurDao.findUtilisateurById(anyInt())).thenReturn(utilisateur);
        when(compteDao.saveCompte(compte)).thenReturn(compte);
        compteDao.saveCompte(compte);
        assertThat(result).isEqualTo("creerCompte");

    }
}
