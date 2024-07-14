package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;
import java.util.Date;

/**
 * Metodo per salvare una nuova discussione
 */
@WebServlet("/salvaDiscussione")
public class SalvaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titolo = req.getParameter("titolo");
        String categoria = req.getParameter("idCategoria");
        String corpo = req.getParameter("corpo");
        String destinazione = "login.jsp";
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        /*Controlla se tutti i parametri necessari siano stati passati*/
        if(titolo!=null && categoria !=null && corpo!=null && l!=null){
            if(!titolo.isEmpty() && !corpo.isEmpty()){
                int idCategoria = Integer.parseInt(categoria);
                GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
                Discussione discussione = ds.creaDiscussione(titolo, idCategoria, l.getIdLettore(), corpo);
                destinazione = getServletContext().getContextPath()+"/discussione?idDiscussione=" +discussione.getIdDiscussione();
            }
        }
        /*redirect alla pagina di login oppure la pagine della discussione appena creata*/
        resp.sendRedirect(destinazione);
    }
}
