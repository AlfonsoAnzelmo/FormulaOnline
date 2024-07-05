package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
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
        String idCategoria = req.getParameter("categoria");
        String corpo = req.getParameter("corpo");
        String indirizzo = "/login.jsp";
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        /*Controlla se tutti i parametri necessari siano stati passati*/
        if(titolo!=null && idCategoria!=null && corpo!=null && l!=null){
            if(!titolo.isEmpty() && !corpo.isEmpty()){
                Commento c = new Commento();
                Discussione d = new Discussione();
                Categoria cat = new Categoria();
                GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
                c.setAutore(l);
                c.setCorpo(corpo);
                c.setDataCommento(new Date());
                cat.setIdCategoria(Integer.parseInt(idCategoria));
                d.setCategoria(cat);
                d.setTitolo(titolo);
                d.setNumeroCommenti(0);
                d.setLettore(l);
                ds.creaDiscussione(d, c);
            }
        }
        if(l==null){
            resp.sendRedirect(indirizzo);
        }

    }
}
