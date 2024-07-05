package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.model.dao.CategoriaDAO;
import unisa.it.formulaonline.model.dao.CommentoDAO;
import unisa.it.formulaonline.model.dao.DiscussioneDAO;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.Date;
import java.util.List;

public class GestioneDiscussioneImplementazione implements GestioneDiscussioneSerice {

    private DiscussioneDAO discussioneDAO = new DiscussioneDAO();
    private CommentoDAO commentoDAO = new CommentoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private LettoreDAO lettoreDAO = new LettoreDAO();

    @Override
    public Discussione creaDiscussione(String titolo, int idCategoria, int idAutore, String commento) {
        Categoria categoria = categoriaDAO.doRetrieveById(idCategoria);
        Lettore lettore = lettoreDAO.doRetrieveById(idAutore) ;

        Discussione discussione = discussioneDAO.doSave(new Discussione(1, categoria, titolo, lettore));
        commentoDAO.doSave(new Commento(commento, discussione, new Date(), lettore));

        return discussione;
    }

    @Override
    public Discussione modificaDiscussione(String titolo, int nuovaCategoria, int idDiscussione) {
        Categoria categoria = categoriaDAO.doRetrieveById(nuovaCategoria);

        Discussione discussione = discussioneDAO.doRetrieveById(idDiscussione);
        discussione.setTitolo(titolo);
        discussione.setCategoria(categoria);
        return discussioneDAO.doUpdate(discussione, idDiscussione);
    }

    @Override
    public void eliminaDiscussione(int idDiscussione) {
        discussioneDAO.doDelete(idDiscussione);
    }

    @Override
    public List<Discussione> ottieniDiscussioniDaCategoria(int idCategoria) {
        Categoria categoria = categoriaDAO.doRetrieveById(idCategoria);
        return discussioneDAO.doRetrieveAllByCategoria(categoria) ;

    }

    @Override
    public Commento aggiungiCommento(int idDiscussione, String corpo, int idAutore) {
        Discussione discussione = discussioneDAO.doRetrieveById(idDiscussione);
        Lettore lettore = lettoreDAO.doRetrieveById(idAutore);
        discussione.setNumeroCommenti(discussione.getNumeroCommenti() + 1);
        return commentoDAO.doSave(new Commento(corpo, discussione, new Date(), lettore));

    }

    @Override
    public Commento modificaCommento(String corpo, int idCommento) {
        Commento commento = commentoDAO.doRetrieveById(idCommento);
        commento.setCorpo(corpo);
        return commentoDAO.doUpdate(commento, idCommento);
    }

    @Override
    public void rimuoviCommento(int idCommento, int idDiscussione) {
        Discussione discussione = discussioneDAO.doRetrieveById(idDiscussione);
        commentoDAO.doDelete(idCommento);
        discussione.setNumeroCommenti(discussione.getNumeroCommenti() + 1);
    }
}
