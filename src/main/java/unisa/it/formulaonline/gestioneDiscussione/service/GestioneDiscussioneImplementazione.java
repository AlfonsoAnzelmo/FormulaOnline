package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.autenticazione.service.AreaUtenteService;
import unisa.it.formulaonline.autenticazione.service.AreaUtenteServiceImpl;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneService;
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
    private GestioneCategoriaDiscussioneService gestioneCategoriaDiscussione = new GestioneCategoriaDiscussioneImplementazione();
    private AreaUtenteService areaUtenteService = new AreaUtenteServiceImpl();

    @Override
    public Discussione creaDiscussione(String titolo, int idCategoria, int idAutore, String commento) {
        Categoria categoria = gestioneCategoriaDiscussione.ottienieCategoriaDaId(idCategoria);
        Lettore lettore = areaUtenteService.ottieniLettoreDaId(idAutore) ;

        Discussione discussione = discussioneDAO.doSave(new Discussione(1, categoria, titolo, lettore));
        commentoDAO.doSave(new Commento(commento, discussione, new Date(), lettore));

        return discussione;
    }

    @Override
    public Discussione modificaDiscussione(String titolo, int nuovaCategoria, int idDiscussione) {
        Categoria categoria = gestioneCategoriaDiscussione.ottienieCategoriaDaId(nuovaCategoria);

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
        Categoria categoria = gestioneCategoriaDiscussione.ottienieCategoriaDaId(idCategoria);
        return discussioneDAO.doRetrieveAllByCategoria(categoria) ;

    }

    @Override
    public Commento aggiungiCommento(int idDiscussione, String corpo, int idAutore) {
        Discussione discussione = discussioneDAO.doRetrieveById(idDiscussione);
        Lettore lettore = areaUtenteService.ottieniLettoreDaId(idAutore);
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
