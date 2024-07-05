package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;

import java.util.List;

public interface GestioneCategoriaDiscussioneService {

    Categoria creaCategoriaDiscussione(Categoria categoria) ;
    Categoria modificaCategoriaDiscussione(Categoria categoria, int idCategoria) ;
    void cancellaCategoriaDiscussione(int idCategoria) ;
}
