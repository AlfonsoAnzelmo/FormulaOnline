package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneService;
import unisa.it.formulaonline.model.dao.CommentoDAO;
import unisa.it.formulaonline.model.dao.DiscussioneDAO;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;

import java.util.List;

public class GestioneDiscussioneImplementazione implements GestioneSegnalazioneService {

    private DiscussioneDAO discussioneDAO = new DiscussioneDAO();
    private CommentoDAO commentoDAO = new CommentoDAO();


    Discussione creaDiscussione(Discussione discussione, Commento commento) {
        discussione.setNumeroCommenti(discussione.getNumeroCommenti() + 1);
        discussioneDAO.doSave(discussione, commento);
        return discussione;
    }

    Discussione modificaDiscussione(Discussione discussione, int idDiscussione) {
        discussioneDAO.doUpdate(discussione, idDiscussione);
        return discussione;
    }

    Discussione cancellaDiscussione(int idDiscussione) {
        discussioneDAO.doDelete(idDiscussione);
        return discussioneDAO.doRetrieveById(idDiscussione);
    }

    List<Discussione> ottieniDiscussioniDaCategoria(Categoria categoria) {
        return discussioneDAO.doRetrieveAllByCategoria(categoria);
    }

    Commento creaCommento(Commento commento) {
        commentoDAO.doSave(commento);
        return commento;
    }

    Commento modificaCommento(Commento commento){
        commentoDAO.doUpdate(commento, commento.getIdCommento());
        return commento;
    }

    void rimuoviCommento(Commento commento) {
        commentoDAO.doDelete(commento.getIdCommento());
    }

}
