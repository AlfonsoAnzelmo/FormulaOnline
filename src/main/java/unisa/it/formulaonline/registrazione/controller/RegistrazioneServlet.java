package unisa.it.formulaonline.registrazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.registrazione.service.RegistrazioneService;
import unisa.it.formulaonline.registrazione.service.RegistrazioneServiceImpl;
import java.io.IOException;

/**
 * Servlet che si occupa della registrazione di nuovi lettori
 */
@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String dest;
        String email=req.getParameter("email");
        String password =  req.getParameter("password");
        String nickname =  req.getParameter("nickname");
        String scuderia = req.getParameter("scuderia");
        //controllo sulle precondizioni delle credenziali
        if (email!=null && password!=null && nickname!=null && scuderia!=null){
            RegistrazioneService rs = new RegistrazioneServiceImpl();
            Lettore l = rs.registraLettore(email, password, nickname, scuderia);
            //se l'utente è stato registrato con successo
            if(l!=null){
                HttpSession session= req.getSession();
                session.setAttribute("lettore", l);
                dest="index.jsp";
                resp.sendRedirect(dest);
            }
        }
        /*  se le credenziali non sono valide o la registrazione non è andata a buon fine
            ritorna alla pagina di registrazione*/
        dest = "registrazione.jsp";
        req.setAttribute("regErr", "Credenziali non valide");
        req.getRequestDispatcher(dest).forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
