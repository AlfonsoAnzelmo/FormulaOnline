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

@WebServlet("/eliminaCategoria")
public class EliminaCategoriaDiscussione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * elimina una categoria
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lettore = req.getParameter("lettore");
        String categoria = req.getParameter("categoria");

        if(lettore!=null && categoria.length() >0){
            Lettore l = (Lettore) req.getSession().getAttribute("lettore");

            int idCategoria = Integer.parseInt(categoria);
            GestioneCategoriaDiscussioneService gestioneCategoriaDiscussioneService = new GestioneCategoriaDiscussioneImplementazione();

            if(l.getModeratore()) {
                GestioneCategoriaDiscussioneService gs = new GestioneCategoriaDiscussioneImplementazione();
                gs.cancellaCategoriaDiscussione(idCategoria);
            }

        }

    }
}
