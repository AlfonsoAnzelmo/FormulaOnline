package unisa.it.formulaonline.gestioneCategoriaDiscussione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

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
        String categoria = req.getParameter("categoria");
        if(l!=null && l.getModeratore() && categoria!=null){
            destinazione ="WEB-INF/moderazione/modificacategoria.jsp";
            RequestDispatcher rd = req.getRequestDispatcher(destinazione);
            rd.forward(req, resp);
        }
        resp.sendRedirect(destinazione);
    }
}
