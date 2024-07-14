package unisa.it.formulaonline.gestioneCategoriaDiscussione.controller;

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

import java.io.IOException;
import java.util.List;

/**
 * Servlet per visualizzare una categoria con le sottocategorie e le discussioni che contiene, dato il suo id
 */
@WebServlet("/categoria")
public class VisualizzaCategoriaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoriaStr = req.getParameter("idCategoria");
        String destinazione = "/";
        if(categoriaStr!=null){
            int idCategoria = Integer.parseInt(categoriaStr);
            GestioneCategoriaDiscussioneService cs = new GestioneCategoriaDiscussioneImplementazione();
            GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
            Categoria categoria = cs.ottieniCategoriaDaId(idCategoria);
            List<Categoria> sottocategorie = cs.ottieniSottocategorie(idCategoria);
            List<Discussione> discussioni = ds.ottieniDiscussioniDaCategoria(idCategoria);
            destinazione = "categoria.jsp";
            req.setAttribute("categoria", categoria);
            req.setAttribute("sottocategorie", sottocategorie);
            req.setAttribute("discussioni", discussioni);
            RequestDispatcher rd = req.getRequestDispatcher(destinazione);
            rd.forward(req, resp);
        }
        else{
            resp.sendRedirect(destinazione);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
