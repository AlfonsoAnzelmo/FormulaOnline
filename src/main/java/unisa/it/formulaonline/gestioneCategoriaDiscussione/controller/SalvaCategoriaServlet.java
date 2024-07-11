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

@WebServlet("/salvaCategoria")
public class SalvaCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lettore = req.getParameter("lettore");
        String nome = req.getParameter("nome");
        String descrizione = req.getParameter("descrizione");
        String idPadreStr = req.getParameter("idPadre");
        String destinazione = "home";
        if (lettore != null && nome != null && descrizione != null
                && idPadreStr != null) {
            if (nome.length() >= 5 && nome.length() <= 50 && descrizione.length() <= 300) {
                Lettore l = (Lettore) req.getSession().getAttribute("lettore");
                if (l.getModeratore()) {
                    int categoriaPadreID = Integer.parseInt(idPadreStr);
                    GestioneCategoriaDiscussioneService gs = new GestioneCategoriaDiscussioneImplementazione();
                    Categoria categoria =
                            gs.creaCategoriaDiscussione(nome, descrizione, categoriaPadreID, l.getIdLettore());
                    destinazione =  getServletContext().getContextPath() + "/categoria?idCategoria=" +
                            categoria.getIdCategoria();
                }
            }
        }
        resp.sendRedirect(destinazione);
    }
}