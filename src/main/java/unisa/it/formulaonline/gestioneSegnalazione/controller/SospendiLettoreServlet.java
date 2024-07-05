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
import java.sql.Date;

public class SospendiLettoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lp = req.getParameter("lettore");
        String cp = req.getParameter("commento");
        String sp = req.getParameter("segnalazione");

        if(lp!=null && cp!=null){
            Lettore m = (Lettore) req.getSession().getAttribute("lettore");

            if (m!=null && m.getModeratore()){
                int idA = Integer.parseInt(lp);
                int idC = Integer.parseInt(cp);
                int idS = Integer.parseInt(sp);
                GestioneSegnalazioneService gs = new GestioneSegnalazioneServiceImpl();
                Lettore l = new Lettore();
                l.setIdLettore(idA);
                Commento c = new Commento();
                c.setIdCommento(idC);
                Segnalazione s = new Segnalazione(idS, c, l, null);
                Date data = Date.valueOf(req.getParameter("data"));
                gs.sospendiLettore(s, data);
            }
        }
    }
}
