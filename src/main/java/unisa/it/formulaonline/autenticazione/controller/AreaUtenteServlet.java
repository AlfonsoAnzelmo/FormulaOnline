package unisa.it.formulaonline.autenticazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

/**
 * Servlet per visualizzare la propria area utente e poter modificare i propri dati
 */
@WebServlet("/areaUtente")
public class AreaUtenteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        //di base se vuole visualizzare il proprio profilo
        String dest = "/login";
        //controlla se il lettore è loggato
        if (l!=null){
            //se è un moderatore lo invia alla pagina di moderazione
            if(l.getModeratore())
                dest = "./WEB-INF/moderazione/moderatore.jsp";
            else
                dest = "./WEB-INF/profile.jsp";
            req.getRequestDispatcher(dest).forward(req, resp);
        }
        //se non è loggato lo redireziona verso la login page
        resp.sendRedirect(dest);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
