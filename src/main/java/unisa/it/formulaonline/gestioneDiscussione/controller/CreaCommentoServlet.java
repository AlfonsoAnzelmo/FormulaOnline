package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

/**
 * Servlet per la creazione di un nuovo commento in una discussione
 */
@WebServlet("/commenta")
public class CreaCommentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore lettore = (Lettore) req.getSession().getAttribute("lettore");
        String corpo = req.getParameter("corpo");
        String idDiscussioneStr = req.getParameter("idDiscussione");
        String destinazione = "index.jsp";
        if(lettore!=null && corpo !=null && idDiscussioneStr!=null){
            int idDiscussione = Integer.parseInt(idDiscussioneStr);
            GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
            ds.aggiungiCommento(idDiscussione, corpo, lettore.getIdLettore());
            destinazione = getServletContext().getContextPath()+"/discussione?idDiscussione=" + idDiscussione;
        }
        resp.sendRedirect(destinazione);
    }
}
