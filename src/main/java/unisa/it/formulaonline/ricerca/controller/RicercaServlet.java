package unisa.it.formulaonline.ricerca.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.ricerca.service.RicercaService;
import unisa.it.formulaonline.ricerca.service.RicercaServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * Servlet che permette di effettuare la ricerca delle discussioni all'url "ricerca"
 * utilizzando un parametro "q" e la pagina ricerca.jsp
 */
@WebServlet("/ricerca")
public class RicercaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("q");
        RicercaService rs= new RicercaServiceImpl();
        List<Discussione> risultati = rs.ricerca(query);
        String dest = "ricerca.jsp";
        req.setAttribute("risultati", risultati);
        RequestDispatcher rd = req.getRequestDispatcher(dest);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
