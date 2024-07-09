package unisa.it.formulaonline.autenticazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import unisa.it.formulaonline.autenticazione.service.AutenticazioneService;
import unisa.it.formulaonline.autenticazione.service.AutenticazioneServiceImpl;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

/**
 * servlet per effettuare l'accesso da parte di lettori e moderatori
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
//        String hash=PasswordHasher(req.getParameter("password")); //se utilizziamo gli hash
        String password = req.getParameter("password");
        AutenticazioneService as = new AutenticazioneServiceImpl();
        Lettore l;
//        l=ld.doRetrieveByEmailPassword(email, hash);
        l = as.login(email, password);
        String dest = "index.jsp";

        //se l'utente esiste ed è valido
        if (l != null) {
            HttpSession session = req.getSession();
            session.setAttribute("lettore", l);
            dest = "index.jsp";
            resp.sendRedirect(dest);
        } else {
//        se le credenziali non sono valide, ritorna alla pagina di login
            dest = "login.jsp";
            req.setAttribute("loginErr", "Credenziali non valide");//metti caso
            req.getRequestDispatcher(dest).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}