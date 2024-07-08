package unisa.it.formulaonline.gestioneCategoriaDiscussione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/categoria")
public class visualizzaCategoriaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoriaStr = req.getParameter("idCategoria");
        String destinazione = "index.jsp";
        if(categoriaStr!=null){
            destinazione = "categoria.jsp";
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
