package unisa.it.formulaonline.gestioneCategoriaDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneService;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

@WebServlet("/aggiornaCategoria")
public class AggiornaCategoriaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * modifica una categoria
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore lettore = (Lettore) req.getSession().getAttribute("lettore");
        String nome = req.getParameter("nome");
        String descrizione = req.getParameter("descrizione");
        String categoriaPadre = req.getParameter("categoriaPadre");
        String categoria = req.getParameter("categoria");
        String destinazione = "home";
        if (lettore != null && nome.length() >= 5 && nome.length() <= 50 &&
                descrizione.length()<=300 && !categoria.isEmpty()) {
            int idCategoriaPadre = 0;
            if(!categoriaPadre.isEmpty()) {
                idCategoriaPadre = Integer.parseInt(categoriaPadre);
            }
            int idCategoria = Integer.parseInt(categoria);

            if (lettore.getModeratore()) {
                GestioneCategoriaDiscussioneService gs = new GestioneCategoriaDiscussioneImplementazione();
                gs.modificaCategoriaDiscussione(idCategoria, nome, descrizione, idCategoriaPadre);
                destinazione=getServletContext().getContextPath()+"/categoria?idCategoria="+idCategoria;
            }
        }
        resp.sendRedirect(destinazione);
    }
}
