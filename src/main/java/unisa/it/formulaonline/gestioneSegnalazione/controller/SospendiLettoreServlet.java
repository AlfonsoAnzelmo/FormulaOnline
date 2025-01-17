package unisa.it.formulaonline.gestioneSegnalazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneService;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneServiceImpl;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/sospendi")
public class SospendiLettoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String segnPar = req.getParameter("codice");
        String dataPar = req.getParameter("dataFineSospensione");

        if(segnPar!=null && dataPar!=null){
            Lettore mod = (Lettore) req.getSession().getAttribute("lettore");
            if (mod!=null && mod.getModeratore()){
                int idSegn = Integer.parseInt(segnPar);
                Date data = Date.valueOf(dataPar);
                GestioneSegnalazioneService gs = new GestioneSegnalazioneServiceImpl();
                gs.sospendiLettore(idSegn, data);
            }
        }
        String destinazione = "segnalazioni";
        resp.sendRedirect(destinazione);
    }
}
