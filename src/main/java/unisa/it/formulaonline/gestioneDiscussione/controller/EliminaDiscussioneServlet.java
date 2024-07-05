package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

public class EliminaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idDiscussioneStr = req.getParameter("idDiscussione");
        Lettore lettore = (Lettore) req.getSession().getAttribute("lettore");
        String indirizzo = "/index.jsp";
        if(idDiscussioneStr != null && lettore != null){   //controlla che i parametri siano validi
            if(lettore.getModeratore()){
                GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
                int idDiscussione = Integer.parseInt(idDiscussioneStr);
                ds.cancellaDiscussione(idDiscussione);
            }
        }
        resp.sendRedirect(indirizzo);
    }
}
