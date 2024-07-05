package unisa.it.formulaonline.gestioneSegnalazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneService;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneServiceImpl;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

/**
 * Servlet per permettere la visualizzazione delle segnalazioni per i moderatori
 */
//TODO link
public class VisualizzaSegnalazioniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore l= (Lettore) req.getSession().getAttribute("lettore");
        if (l.getModeratore()){
            GestioneSegnalazioneService gs=new GestioneSegnalazioneServiceImpl();
            gs.ottieniSegnalazioni();
        }
    }
}
