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
 * Permette di effettuare le ricerche delle discussioni
 */

@WebServlet("/ricerca")
public class RicercaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titolo = req.getParameter("titolo");
        RicercaService rs= new RicercaServiceImpl();
        List<Discussione> risultati = rs.ricerca(titolo);
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
