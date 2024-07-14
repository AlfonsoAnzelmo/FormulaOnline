package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.model.dao.CategoriaDAO;
import unisa.it.formulaonline.model.dao.CommentoDAO;
import unisa.it.formulaonline.model.dao.DiscussioneDAO;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;

import java.util.List;

public class GestioneDiscussioneImplementazione implements GestioneDiscussioneService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Discussione creaDiscussione(String titolo, int idCategoria, int idLettore, String commento) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        Discussione discussione = discussioneDAO.doSave(titolo, idLettore, idCategoria);
        aggiungiCommento(discussione.getIdDiscussione(), commento, idLettore);
        return discussione;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Discussione modificaDiscussione(String titolo, int nuovaCategoria, int idDiscussione) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        return discussioneDAO.doUpdate(idDiscussione, titolo, nuovaCategoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminaDiscussione(int idDiscussione) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        discussioneDAO.doDelete(idDiscussione);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Discussione ottieniDiscussioneDaId(int idDiscussione) {

        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        return discussioneDAO.doRetrieveById(idDiscussione) ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Discussione> ottieniDiscussioniDaCategoria(int idCategoria) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        return discussioneDAO.doRetrieveByCategoria(idCategoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Discussione> ottieniDiscussioniPrincipali(){
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        return discussioneDAO.doRetrieveRecenti();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Commento aggiungiCommento(int idDiscussione, String corpo, int idAutore) {
        CommentoDAO commentoDAO = new CommentoDAO();
        Commento commento = commentoDAO.doSave(idDiscussione, idAutore, corpo);
        return commento;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Commento modificaCommento(String corpo, int idCommento) {
        CommentoDAO commentoDAO = new CommentoDAO();

        Commento commento = commentoDAO.doRetrieveById(idCommento);
        commento.setCorpo(corpo);
        return commentoDAO.doUpdate(commento, idCommento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rimuoviCommento(int idCommento, int idDiscussione) {
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        CommentoDAO commentoDAO = new CommentoDAO();

        Discussione discussione = discussioneDAO.doRetrieveById(idDiscussione);
        commentoDAO.doDelete(idCommento);
        discussione.setNumeroCommenti(discussione.getNumeroCommenti() - 1);
        if (discussione.getNumeroCommenti()<=0){
            eliminaDiscussione(discussione.getIdDiscussione());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Commento ottieniCommentoDaId(int idCommento){
        CommentoDAO commentoDAO = new CommentoDAO();
        Commento commento = commentoDAO.doRetrieveById(idCommento);
        return commento;
    }

    /**
     * {@inheritDoc}
     */
    public List<Commento> ottieniCommentiDaDiscussione(int idDiscussione){
        List<Commento> commenti;
        CommentoDAO commentoDAO = new CommentoDAO();
        commenti = commentoDAO.doRetrieveByDiscussione(idDiscussione);
        return commenti;
    }
}
