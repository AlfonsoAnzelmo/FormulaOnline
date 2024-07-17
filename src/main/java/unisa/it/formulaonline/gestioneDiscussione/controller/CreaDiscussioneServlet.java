package unisa.it.formulaonline.gestioneDiscussione.controller;

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

/**
 * Servlet che riporta alla pagina di creazione discussione
 */
@WebServlet("/creaDiscussione")
public class CreaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catStr = req.getParameter("idCategoria");
        String destinazione = "login.jsp";
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        if(catStr!=null && l!=null){
            GestioneCategoriaDiscussioneService cs = new GestioneCategoriaDiscussioneImplementazione();
            int catId = Integer.parseInt(catStr);
            Categoria categoria = cs.ottieniCategoriaDaId(catId);
            if(categoria != null){
                destinazione = "creadiscussione.jsp";
                req.setAttribute("idCategoria", categoria.getIdCategoria());
                RequestDispatcher rd = req.getRequestDispatcher(destinazione);
                rd.forward(req, resp);
            }
            else{
                resp.sendRedirect("home");
            }
        }
        else {
            resp.sendRedirect(destinazione);
        }
    }
}
