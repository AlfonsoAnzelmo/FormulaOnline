package unisa.it.formulaonline.gestioneCategoriaDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneService;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

/**
 * Servlet per salvare una nuova categoria
 */
@WebServlet("/salvaCategoria")
public class SalvaCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore lettore = (Lettore) req.getSession().getAttribute("lettore");
        String nome = req.getParameter("nome");
        String descrizione = req.getParameter("descrizione");
        String idPadreStr = req.getParameter("categoria");
        String destinazione = "home";
        if (lettore != null && nome != null && descrizione != null
                && idPadreStr != null) {
            if (nome.length() >= 5 && nome.length() <= 50 && descrizione.length() <= 300) {
                if (lettore.getModeratore()) {
                    int categoriaPadreID = Integer.parseInt(idPadreStr);
                    GestioneCategoriaDiscussioneService gs = new GestioneCategoriaDiscussioneImplementazione();
                    Categoria categoria =
                            gs.creaCategoriaDiscussione(nome, descrizione, categoriaPadreID, lettore.getIdLettore());
                    destinazione =  getServletContext().getContextPath() + "/categoria?idCategoria=" +
                            categoria.getIdCategoria();
                }
            }
        }
        resp.sendRedirect(destinazione);
    }
}