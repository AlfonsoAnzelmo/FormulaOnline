package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.model.entity.Categoria;

import java.util.List;

public interface GestioneCategoriaDiscussioneService {

    /**
     * crea una nuova categoria
     * @param nome nome della categoria
     * @param descrizione descrizione della tipologia delle discussione che dovrà contenere la categoria
     * @param categoriaPadre categoria padre, se non appartiene a nessuna categoria allora 0
     * @param autore lettore che ha creato la categoria
     * @return la categoria creata
     */
    Categoria creaCategoriaDiscussione(String nome, String descrizione, int categoriaPadre, int autore) ;

    /**
     * modifica una nuova categoria
     * @param idCategoria id della categoria da modificare
     * @param nome nuovo nome della categoria
     * @param descrizione nuova descrizione della categoria
     * @param categoriaPadre id della categoria a cui dovrà appartenere
     * @return la categoria modificata
     */
    Categoria modificaCategoriaDiscussione(int idCategoria, String nome, String descrizione, int categoriaPadre) ;

    /**
     * elimina una nuova categoria
     * @param idCategoria id della categoria da eliminare
     */
    void eliminaCategoria(int idCategoria);

    /**
     * ottiene una categoria dall'id
     * @param idCategoria l'id della categoria
     * @return la categoria cercata
     */
    Categoria ottieniCategoriaDaId(int idCategoria);

    /**
     * Restituisce la lista delle categorie che non sono sottocategorie
     */
    List<Categoria> ottieniCategoriePrincipali();

    /**
     * Restituisce le sottocategorie di una categoria
     * @param idCategoria l'id della categoria di cui vogliamo recuperare le sottocategorie
     */
    List<Categoria> ottieniSottocategorie(int idCategoria);

    /**
     * Restituisce una lista di tutte le categorie
     * @return una lista di tutte le categorie
     */
    List<Categoria> ottieniTutteCategorie();
}
