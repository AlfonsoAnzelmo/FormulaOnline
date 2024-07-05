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

@WebServlet("/modificaCategoria")
public class ModifcaCategoriaDiscussioneServlet extends HttpServlet {
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
        String lettore = req.getParameter("lettore");
        String categoriaNome = req.getParameter("categoriaNome");
        String categoriaDescrizione = req.getParameter("categoriaDescrizione");
        String categoriaPadre = req.getParameter("categoriaPadre");
        String categoria = req.getParameter("categoriaPadre");

        if (lettore != null && categoriaNome.length() > 0 && categoriaDescrizione.length() > 0
                && categoriaPadre.length() > 0 && categoria.length() > 0) {
            Lettore l = (Lettore) req.getSession().getAttribute("lettore");

            int idCategoriaPadre = Integer.parseInt(categoriaPadre);
            int idCategoria = Integer.parseInt(categoria);


            if (l.getModeratore()) {
                GestioneCategoriaDiscussioneService gs = new GestioneCategoriaDiscussioneImplementazione();
                gs.modificaCategoriaDiscussione(idCategoria, categoriaNome, categoriaDescrizione, idCategoriaPadre);
            }

        }

    }
}
