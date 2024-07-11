package unisa.it.formulaonline;

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

@WebServlet(urlPatterns = {"", "/home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GestioneCategoriaDiscussioneService cs = new GestioneCategoriaDiscussioneImplementazione();
        GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
        List<Categoria> categorie = cs.ottieniCategoriePrincipali();
        List<Discussione> discussioni = ds.ottieniDiscussioniPrincipali();
        req.setAttribute("categorie", categorie);
        req.setAttribute("discussioniPrincipali", discussioni);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
