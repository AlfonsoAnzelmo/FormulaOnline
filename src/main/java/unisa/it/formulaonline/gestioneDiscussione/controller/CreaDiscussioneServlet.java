package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneSerice;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;
import java.util.Date;

@WebServlet("/creaDiscussione")
public class CreaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titolo = req.getParameter("titolo");
        String categoria = req.getParameter("categoria");
        String corpo = req.getParameter("corpo");
        String indirizzo = "/login.jsp";

        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        /*Controlla se tutti i parametri necessari siano stati passati*/
        if(titolo!=null && categoria !=null && corpo!=null && l!=null){
            if(!titolo.isEmpty() && !corpo.isEmpty()){

                int idCategoria = Integer.parseInt(categoria) ;
                GestioneDiscussioneSerice ds = new GestioneDiscussioneImplementazione();
                ds.creaDiscussione(titolo, idCategoria, l.getIdLettore(), corpo);
            }
        }
        if(l==null){
            resp.sendRedirect(indirizzo);
        }

    }
}
