package unisa.it.formulaonline.gestioneSegnalazione.controller;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/segnalazioni")
public class VisualizzaSegnalazioniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        String dest="/WEB-INF/permission-error.jsp";
        if(l!=null && l.getModeratore()){
            GestioneSegnalazioneService gs = new GestioneSegnalazioneServiceImpl();
            List<Segnalazione> ls = gs.visualizzaSegnalazioni();
            dest = "segnalazioni.jsp";
            req.setAttribute("segnalazioni", ls);
            RequestDispatcher rd = req.getRequestDispatcher(dest);
            rd.forward(req, resp);
        }
        resp.sendRedirect(dest);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
