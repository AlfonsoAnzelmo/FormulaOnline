package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;

import java.io.IOException;

/**
 * Servlet per aggiornare le informazioni di una discussione
 */
@WebServlet("/aggiornaDiscussione")
public class AggiornaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idDiscussione");
        String titolo = req.getParameter("titolo");
        String categoriaStr = req.getParameter("categoria");
        String numComStr = req.getParameter("numCommenti");
        String autoreStr = req.getParameter("autore");
        String destinazione = "index.jsp";
        /* controlla che i parametri esistano nella richiesta */
        if(idStr!=null && titolo!=null && categoriaStr!=null && numComStr!= null && autoreStr!=null){
            GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
            int idDiscussione = Integer.parseInt(idStr);
            int idCat = Integer.parseInt(categoriaStr);
            ds.modificaDiscussione(titolo, idCat, idDiscussione);
            destinazione = getServletContext().getContextPath()+"/discussione?idDiscussione=" +idDiscussione;
        }
        resp.sendRedirect(destinazione);
    }
}
