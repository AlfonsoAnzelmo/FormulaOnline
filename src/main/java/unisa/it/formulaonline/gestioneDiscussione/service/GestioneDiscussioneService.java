package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.List;

public interface GestioneDiscussioneService {
    /**
     * Crea una nuova discussione se i parametri inseriti sono validi
     * @param titolo della discussione
     * @param idCategoria id della categoria a cui dovrà appartenere
     * @param idAutore id del lettore che l'ha creata
     * @param commento primo commento della discussione
     * @return la discussione creata
     */
    Discussione creaDiscussione(String titolo, int idCategoria, int idAutore, String commento) ;

    /**
     * Modifica della discussione
     * @param titolo nuovo titolo della discussione
     * @param nuovaCategoria nuova categoria a cui dovrà appartenere
     * @param idDiscussione id della discussione da modificare
     * @return la discussione con i parametri aggiornati
     */
    Discussione modificaDiscussione(String titolo, int nuovaCategoria, int idDiscussione) ;

    /**
     * Elimina una discussione partendo dal suo id
     * @param idDiscussione della discussione da eliminare
     */
    void eliminaDiscussione(int idDiscussione) ;

    /**
     * Recupera le discussioni appartenenti ad una categoria
     * @param idCategoria a cui appartengono le discussioni
     * @return la lista delle discussioni
     */
    List<Discussione> ottieniDiscussioniDaCategoria(int idCategoria);

    /**
     * Metodo per recuperare la lista delle discussioni con le risposte più recenti
     * @return la lista delle discussioni, dall'ultima risposta più recente
     */
    List<Discussione> ottieniDiscussioniPrincipali();

    /**
     * Aggiunge un nuovo commento ad una discussione
     * @param idDiscussione id della discussione commentata
     * @param corpo corpo del commento
     * @param idAutore id dell'autore del commento
     * @return il commento se è stato creato con successo
     */
    Commento aggiungiCommento(int idDiscussione, String corpo, int idAutore);

    /**
     * Metodo per la modifica di un commento esistente da parte del creatore
     * Non implementato
     * @param corpo nuovo corpo del commento
     * @param idCommento id del commento modificato
     * @return il commento con i campi aggiornati
     */
    Commento modificaCommento(String corpo, int idCommento);

    /**
     * Metodo per la rimozione di un commento da una discussione
     * @param idCommento id del commento da eliminare
     * @param idDiscussione id della discussione a cui appartiene il commento
     */
    void rimuoviCommento(int idCommento, int idDiscussione);

    /**
     * metodo per recuperare una discussione dal suo id
     * @param idDiscussione id della discussione da recuperare
     * @return la discussione relativa all'id, null se non esiste
     */
    Discussione ottieniDiscussioneDaId(int idDiscussione);

    /**
     * Recupera i commenti dato un id
     * @param idCommento id del commento da recuperare
     * @return il commento con il rispettivo id, null se non esiste
     */
    Commento ottieniCommentoDaId(int idCommento);

    /**
     * Recupera i commenti di una discussione
     * @param idDiscussione id della discussione da voler recuperare
     * @return i commenti alla discussione
     */
    List <Commento> ottieniCommentiDaDiscussione(int idDiscussione);
}
