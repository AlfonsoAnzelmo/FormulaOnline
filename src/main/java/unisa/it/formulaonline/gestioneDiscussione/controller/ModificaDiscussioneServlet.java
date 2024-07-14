package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneService;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;
import java.util.List;

/**
 * Servlet per la modifica di una discussione ed il salvataggio di tali modifiche
 */
@WebServlet("/modificaDiscussione")
public class ModificaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDiscussione = Integer.parseInt(req.getParameter("idDiscussione"));
        GestioneDiscussioneService gestioneDiscussioneService = new GestioneDiscussioneImplementazione();
        Discussione discussione = gestioneDiscussioneService.ottieniDiscussioneDaId(idDiscussione);

        GestioneCategoriaDiscussioneService gestioneCategoriaDiscussioneService = new GestioneCategoriaDiscussioneImplementazione();
        List<Categoria> categoriaList = gestioneCategoriaDiscussioneService.ottieniTutteCategorie();

        req.setAttribute("discussione", discussione);
        req.setAttribute("categorie", categoriaList);

        req.getRequestDispatcher("modificadiscussione.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        String discussione = req.getParameter("idDiscussione");
        String categoria = req.getParameter("categoria");
        String titolo = req.getParameter("titolo");

        int idDiscussione = Integer.parseInt(discussione);
        int idCategoria = Integer.parseInt(categoria);

        GestioneDiscussioneService gestioneDiscussioneService = new GestioneDiscussioneImplementazione();
        gestioneDiscussioneService.modificaDiscussione(titolo, idCategoria, idDiscussione);

        if(l!=null && l.getModeratore() && discussione!=null){
            resp.sendRedirect("home");
        }
        resp.sendRedirect("home");
    }
}
