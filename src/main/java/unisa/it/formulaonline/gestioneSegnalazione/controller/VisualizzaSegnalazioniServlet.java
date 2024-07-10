package unisa.it.formulaonline.gestioneSegnalazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneService;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneServiceImpl;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.model.entity.Segnalazione;

import java.io.IOException;
import java.util.List;

/**
 * Servlet per permettere la visualizzazione delle segnalazioni per i moderatori
 */
//TODO link
@WebServlet("/segnalazioni")
public class VisualizzaSegnalazioniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore l= (Lettore) req.getSession().getAttribute("lettore");
        String destinazione = "index.jsp";
        if (l!=null && l.getModeratore()){
            GestioneSegnalazioneService gs=new GestioneSegnalazioneServiceImpl();
            List<Segnalazione> segnalazioni = gs.ottieniSegnalazioni();
            destinazione = "/WEB-INF/moderazione/segnalazioni.jsp";
            req.setAttribute("segnalazioni", segnalazioni);
            req.getRequestDispatcher(destinazione).forward(req, resp);
        }
        else{
            resp.sendRedirect(destinazione);
        }
    }
}
