package ControllerTest;

import com.payMayBuddy.payMayBuddy.Service.Dao.UtilisateurDao;
import com.payMayBuddy.payMayBuddy.Service.Impl.UtilisateurDaoImpl;
import com.payMayBuddy.payMayBuddy.controller.UtilisateurController;
import com.payMayBuddy.payMayBuddy.model.Utilisateur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UtilisateurDao.class, UtilisateurController.class})
public class UtilisateurTest {

    @MockBean
    UtilisateurDao utilisateurDao;
    @Autowired
    UtilisateurController controller;


    Utilisateur utilisateur = new Utilisateur(1, "Benny", "Etilefanela", "etilebenny@gmail.com", "078962852", "benny");

    @Test
    public void getEmailUserTest() throws SQLException {
        when(utilisateurDao.findUtilisateurByEmail(utilisateur.getEmail())).thenReturn(utilisateur);
        assertThat(utilisateur.getEmail()).isEqualTo("etilebenny@gmail.com");
    }

    @Test
    public void getUserByIdTest() throws SQLException {
        when(utilisateurDao.findUtilisateurById(1)).thenReturn(utilisateur);
        assertThat(utilisateur.getPrenom()).isEqualTo("Benny");
    }

    @Test
    public void getEmailAndPassword() throws SQLException {
        when(utilisateurDao.getEmailAndPasswordForUser("etilebenny@gmail.com", "benny")).thenReturn(utilisateur);
        assertThat(utilisateur).isNotNull();
    }

    @Test
    public void saveUserTest() throws SQLException {
       Utilisateur utilisateur = new Utilisateur(1, "Benny", "Etilefanela", "etilebenny@gmail.com", "078962852", "benny");

        when(utilisateurDao.createUtilisateur(utilisateur)).thenReturn(utilisateur);
        assertThat(utilisateur.getPrenom()).isEqualTo("Benny");
    }

    @Test
    public void loadLoginPageTest()throws SQLException{
        HttpSession session =mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        String result = controller.identifyUser(utilisateur,request);
        when(utilisateurDao.getEmailAndPasswordForUser(anyString(),anyString())).thenReturn(utilisateur);
        utilisateurDao.getEmailAndPasswordForUser(utilisateur.getEmail(),"benny");
        assertThat(result).isEqualTo("login");
    }
    @Test
    public void registerUserTest() throws SQLException {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        String result = controller.registrerUser(utilisateur, request);
        when(utilisateurDao.createUtilisateur(utilisateur)).thenReturn(utilisateur);
        assertThat(result).isEqualTo("login");
    }
    @Test
    public void updateUserTest()throws SQLException{

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn( session);
        when(session.getAttribute("id")).thenReturn(1);
        utilisateurDao.updateUtilisateur(1,utilisateur);
        String result = controller.modifierInfoUser(utilisateur,request);
        when(utilisateurDao.updateUser(1)).thenReturn(utilisateur);
        assertThat(result).isEqualTo("upadteUser");
    }
    @Test
    public void deleteUserTest()throws SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        String result = controller.supprimerUtilisateurr(1,request);
        when(utilisateurDao.deleteUtilisateur(1)).thenReturn(utilisateur);




    }

}
