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
//        String hash=PasswordHasher(req.getParameter("password")); //se utilizziamo gli hash
        String password =  req.getParameter("password");
        String nickname =  req.getParameter("nickname");
        //controllo sulle precondizioni delle credenziali
        if ((5<=email.length() && (email.length()) <= 50)
                && ((8 <= password.length())) && (password.length() <= 32)
                && (5 <= nickname.length() && nickname.length() <= 30)){
            String scuderia = req.getParameter("scuderia");
            RegistrazioneService rs = new RegistrazioneServiceImpl();
            Lettore l = rs.registraLettore(email, password, nickname, scuderia);
            //se l'utente è stato registrato con successo
            if(l.getIdLettore()!=null){
                HttpSession session= req.getSession();
                session.setAttribute("lettore", l);
                dest="index.jsp";
                resp.sendRedirect(dest);
            }
        }
        //se le credenziali non sono valide, ritorna alla pagina di registrazione
        dest = "registrazione.jsp";
        req.setAttribute("regErr", "Credenziali non valide");//metti caso
        req.getRequestDispatcher(dest).forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
