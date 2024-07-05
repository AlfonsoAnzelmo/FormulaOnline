package unisa.it.formulaonline.gestioneSegnalazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneService;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneServiceImpl;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.model.entity.Segnalazione;

import java.io.IOException;

public class EliminaSegnalazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("autore")!=null && req.getParameter("commento")!=null){
            int idA = Integer.parseInt(req.getParameter("autore"));
            int idC = Integer.parseInt(req.getParameter("commento"));
            GestioneSegnalazioneService gs = new GestioneSegnalazioneServiceImpl();
            Lettore l = new Lettore();
            l.setIdLettore(idA);
            Commento c = new Commento();
            c.setIdCommento(idC);
            Segnalazione s = new Segnalazione(c,l,null);
            gs.eliminaSegnalazione(s);
        }
    }
}
