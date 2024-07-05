package unisa.it.formulaonline.autenticazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.autenticazione.service.LettoreService;
import unisa.it.formulaonline.autenticazione.service.LettoreServiceImpl;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

/**
 * Servlet che permette all'utente di modificare le proprie informazioni
 */
public class AggiornaUtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        String scuderia = req.getParameter("scuderia");
        String dest = "areaUtente.jsp";
        if(5<=email.length() && email.length()<=50 &&
                5<=nickname.length() && nickname.length()<=100 &&
                8<= password.length() && password.length()<=32
        ){
            Lettore l = (Lettore) req.getSession().getAttribute("lettore");
            Lettore aggiornato = new Lettore(l.getIdLettore(), email, password, nickname, scuderia, l.getModeratore());
            LettoreService as = new LettoreServiceImpl();
            l = as.aggiornaLettore(l.getIdLettore(), aggiornato);

            //aggiornamento andato a buon fine
            if(l!= null)
                req.getSession().setAttribute("lettore", l);
        }
        else {
            req.setAttribute("regErr", "Dati non validi");//metti caso
        }
        req.getRequestDispatcher(dest).forward(req, resp);
    }
}
