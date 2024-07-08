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

@WebServlet("/nuovaCategoria")
public class NuovaCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destinazione = "index.jsp";
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        if(l!=null && l.getModeratore()){
            destinazione ="WEB-INF/moderazione/creacategoria.jsp";
            RequestDispatcher rd = req.getRequestDispatcher(destinazione);
            rd.forward(req, resp);
        }
        resp.sendRedirect(destinazione);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
