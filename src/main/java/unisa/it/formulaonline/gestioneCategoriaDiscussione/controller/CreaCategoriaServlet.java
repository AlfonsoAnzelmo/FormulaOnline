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
import java.util.List;

@WebServlet("/creaCategoria")
public class CreaCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        String dest = "home";
        if(l!=null && l.getModeratore()){
            GestioneCategoriaDiscussioneService cs = new GestioneCategoriaDiscussioneImplementazione();
            List<Categoria> categorie = cs.ottieniTutteCategorie();
            req.setAttribute("categorie", categorie);
            dest= "WEB-INF/moderazione/creacategoria.jsp";
            req.getRequestDispatcher(dest).forward(req, resp);
        }
        else{
            resp.sendRedirect(dest);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
