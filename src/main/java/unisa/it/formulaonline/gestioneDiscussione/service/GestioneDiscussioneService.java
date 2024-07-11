package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.List;

public interface GestioneDiscussioneService {
    /**
     * Crea una nuova discussione se i parametri inseriti sono validi
     * @param titolo
     * @param idCategoria
     * @param idAutore
     * @param commento
     * @return la discussione creata
     */
    Discussione creaDiscussione(String titolo, int idCategoria, int idAutore, String commento) ;
    Discussione modificaDiscussione(String titolo, int nuovaCategoria, int idDiscussione) ;
    void eliminaDiscussione(int idDiscussione) ;
    List<Discussione> ottieniDiscussioniDaCategoria(int idCategoria);

    /**
     * Restituisce la lista delle discussioni con le risposte più recenti
     * @return
     */
    List<Discussione> ottieniDiscussioniPrincipali();
    Commento aggiungiCommento(int idDiscussione, String corpo, int idAutore);
    Commento modificaCommento(String corpo, int idCommento);
    void rimuoviCommento(int idCommento, int idDiscussione);

    Discussione ottieniDiscussioneDaId(int idDiscussione);

    Commento ottieniCommentoDaId(int idCommento);

    List <Commento> ottieniCommentiDaDiscussione(int idDiscussione);
}
