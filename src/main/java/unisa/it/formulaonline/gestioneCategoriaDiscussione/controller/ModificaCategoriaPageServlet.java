package unisa.it.formulaonline.gestioneCategoriaDiscussione.controller;

import jakarta.servlet.RequestDispatcher;
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
import java.util.List;

/**
 * Servlet che riporta alla pagina di modifica di una categoria
 */
@WebServlet("/modificaCategoria")
public class ModificaCategoriaPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destinazione = "index.jsp";
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        String categoria = req.getParameter("idCategoria");
        if(l!=null && l.getModeratore() && categoria!=null){
            destinazione ="WEB-INF/moderazione/modificacategoria.jsp";
            GestioneCategoriaDiscussioneService cs = new GestioneCategoriaDiscussioneImplementazione();
            int idCat = Integer.parseInt(categoria);
            Categoria cat = cs.ottieniCategoriaDaId(idCat);
            List<Categoria> categorie = cs.ottieniTutteCategorie();
            categorie.remove(cat);
            req.setAttribute("categoria", cat);
            req.setAttribute("categorie", categorie);
            RequestDispatcher rd = req.getRequestDispatcher(destinazione);
            rd.forward(req, resp);
        }else{
            resp.sendRedirect(destinazione);
        }
    }
}
