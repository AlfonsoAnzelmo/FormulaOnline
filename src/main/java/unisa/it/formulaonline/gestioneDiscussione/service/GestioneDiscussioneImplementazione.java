package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.model.dao.CategoriaDAO;
import unisa.it.formulaonline.model.dao.CommentoDAO;
import unisa.it.formulaonline.model.dao.DiscussioneDAO;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestioneDiscussioneImplementazione implements GestioneDiscussioneService {

    @Override
    public Discussione creaDiscussione(String titolo, int idCategoria, int idAutore, String commento) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        CommentoDAO commentoDAO = new CommentoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        LettoreDAO lettoreDAO = new LettoreDAO();

        Categoria categoria = categoriaDAO.doRetrieveById(idCategoria);
        Lettore lettore = lettoreDAO.doRetrieveById(idAutore) ;

        Discussione discussione = discussioneDAO.doSave(new Discussione(1, categoria, titolo, lettore));
        commentoDAO.doSave(new Commento(commento, discussione, new Date(), lettore));

        return discussione;
    }

    @Override
    public Discussione modificaDiscussione(String titolo, int nuovaCategoria, int idDiscussione) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        Categoria categoria = categoriaDAO.doRetrieveById(nuovaCategoria);

        Discussione discussione = discussioneDAO.doRetrieveById(idDiscussione);
        discussione.setTitolo(titolo);
        discussione.setCategoria(categoria);
        return discussioneDAO.doUpdate(discussione, idDiscussione);
    }

    @Override
    public void eliminaDiscussione(int idDiscussione) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        discussioneDAO.doDelete(idDiscussione);
    }

    /**
     * @param idDiscussione
     * @return
     */
    @Override
    public Discussione ottieniDiscussioneDaId(int idDiscussione) {

        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        return discussioneDAO.doRetrieveById(idDiscussione) ;
    }

    @Override
    public List<Discussione> ottieniDiscussioniDaCategoria(int idCategoria) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        return discussioneDAO.doRetrieveByCategoria(idCategoria);
    }

    @Override
    public List<Discussione> ottieniDiscussioniPrincipali(){
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        return discussioneDAO.doRetrieveRecenti();
    }

    @Override
    public Commento aggiungiCommento(int idDiscussione, String corpo, int idAutore) {
/*        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
*/        CommentoDAO commentoDAO = new CommentoDAO();
/*        LettoreDAO lettoreDAO = new LettoreDAO();

        Discussione discussione = discussioneDAO.doRetrieveById(idDiscussione);
        Lettore lettore = lettoreDAO.doRetrieveById(idAutore);
        discussione.setNumeroCommenti(discussione.getNumeroCommenti() + 1);*/
        return commentoDAO.doSave(idDiscussione, idAutore, corpo);

    }

    @Override
    public Commento modificaCommento(String corpo, int idCommento) {
        CommentoDAO commentoDAO = new CommentoDAO();

        Commento commento = commentoDAO.doRetrieveById(idCommento);
        commento.setCorpo(corpo);
        return commentoDAO.doUpdate(commento, idCommento);
    }

    @Override
    public void rimuoviCommento(int idCommento, int idDiscussione) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        CommentoDAO commentoDAO = new CommentoDAO();

        Discussione discussione = discussioneDAO.doRetrieveById(idDiscussione);
        commentoDAO.doDelete(idCommento);
        discussione.setNumeroCommenti(discussione.getNumeroCommenti() + 1);
    }

    public Commento ottieniCommentoDaId(int idCommento){
        CommentoDAO commentoDAO = new CommentoDAO();
        Commento commento = commentoDAO.doRetrieveById(idCommento);
        return commento;
    }

    public List<Commento> ottieniCommentiDaDiscussione(int idDiscussione){
        List<Commento> commenti;
        CommentoDAO commentoDAO = new CommentoDAO();
        commenti = commentoDAO.doRetrieveByDiscussione(idDiscussione);
        return commenti;
    }
}
