package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;

import java.util.List;

public interface GestioneCategoriaDiscussioneService {

    Categoria creaCategoriaDiscussione(String nome, String descrizione, int categoriaPadre, int autore) ;
    Categoria modificaCategoriaDiscussione(int idCategoria, String nome, String descrizione, int categoriaPadre) ;
    void eliminaCategoria(int idCategoria);

    Categoria retrieveById(int idCategoria);
}
