package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;

import java.util.List;

public interface GestioneCategoriaDiscussioneService {

    /**
     * crea una nuova categoria
     * @param nome
     * @param descrizione
     * @param categoriaPadre
     * @param autore
     * @return la categoria creata
     */
    Categoria creaCategoriaDiscussione(String nome, String descrizione, int categoriaPadre, int autore) ;

    /**
     * modifica una nuova categoria
     * @param idCategoria
     * @param nome
     * @param descrizione
     * @param categoriaPadre
     * @return la categoria modificata
     */
    Categoria modificaCategoriaDiscussione(int idCategoria, String nome, String descrizione, int categoriaPadre) ;

    /**
     * elimina una nuova categoria
     * @param idCategoria
     */
    void eliminaCategoria(int idCategoria);

    /**
     * ottiene una nuova categoria
     * @param idCategoria
     * @return
     */
    Categoria retrieveById(int idCategoria);

    /**
     * ottiene una categoria dall'id
     * @param idCategoria
     * @return la categoria cercata
     */
    Categoria ottieniCategoriaDaId(int idCategoria);
}
