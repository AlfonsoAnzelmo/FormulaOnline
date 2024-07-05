package unisa.it.formulaonline.gestioneCategoriaDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneService;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneService;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneServiceImpl;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.model.entity.Segnalazione;

import java.io.IOException;

@WebServlet("/categoria")
public class CreazioneCategoriaDisussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lettore = req.getParameter("lettore");
        String categoriaNome = req.getParameter("categoriaNome");
        String categoriaDescrizione = req.getParameter("categoriaDescrizione");
        String categoriaPadreID = req.getParameter("categoriaPadre");

        if(lettore!=null && categoriaNome.length() > 0 && categoriaDescrizione.length() > 0
        && categoriaPadreID.length() > 0){
            Lettore l = (Lettore) req.getSession().getAttribute("lettore");

            int idCategoriaPadre = Integer.parseInt(categoriaPadreID);
            GestioneCategoriaDiscussioneService gestioneCategoriaDiscussioneService = new GestioneCategoriaDiscussioneImplementazione();
            Categoria categoriaPadre = gestioneCategoriaDiscussioneService.retrieveById(idCategoriaPadre);

            if(l.getModeratore()) {
                Categoria categoria = new Categoria(categoriaNome, categoriaDescrizione, categoriaPadre, l );
                GestioneCategoriaDiscussioneService gs = new GestioneCategoriaDiscussioneImplementazione();
                gs.creaCategoriaDiscussione(categoria);
            }

        }

    }
}
