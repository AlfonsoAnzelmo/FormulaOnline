package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

public class ModificaDiscussioneServlet extends HttpServlet {
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
        /* controlla che i parametri esistano nella richiesta */
        if(idStr!=null && titolo!=null && categoriaStr!=null && numComStr!= null && autoreStr!=null){
            GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
            int idDiscussione = Integer.parseInt(idStr);
            int idCat = Integer.parseInt(categoriaStr);
            ds.modificaDiscussione(titolo, idCat, idDiscussione);
        }
    }
}
