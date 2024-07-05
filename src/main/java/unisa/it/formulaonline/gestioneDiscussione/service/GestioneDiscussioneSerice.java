package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.List;

public interface GestioneDiscussioneSerice {
    /**
     * crea una nuova discussione
     * @param titolo
     * @param idCategoria
     * @param idAutore
     * @param commento
     * @return la nuova discussione creata
     */
    Discussione creaDiscussione(String titolo, int idCategoria, int idAutore, String commento) ;

    /**
     * modifica una discussione
     * @param titolo
     * @param nuovaCategoria
     * @param idDiscussione
     * @return la discussione modificata
     */
    Discussione modificaDiscussione(String titolo, int nuovaCategoria, int idDiscussione) ;

    /**
     * elimina una discussione partendo dall'id
     * @param idDiscussione
     */
    void eliminaDiscussione(int idDiscussione) ;

    /**
     * ottiene una discussione partendo dall'id
     * @param idCategoria
     * @return
     */
    List<Discussione> ottieniDiscussioniDaCategoria(int idCategoria);

    /**
     * crea una nuovo commento e lo aggiunge ad una discussione
     * @param idDiscussione
     * @param corpo
     * @param idAutore
     * @return il commento creato
     */
    Commento aggiungiCommento(int idDiscussione, String corpo, int idAutore);

    /**
     * modifica il commento di una discussione
     * @param corpo
     * @param idCommento
     * @return il commento modificato
     */
    Commento modificaCommento(String corpo, int idCommento);

    /**
     * rimuove un commento ad una discussione
     * @param idCommento
     * @param idDiscussione
     */
    void rimuoviCommento(int idCommento, int idDiscussione);



}
