package unisa.it.formulaonline.gestioneDiscussione.service;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;

import java.util.List;

public interface GestioneDiscussioneSerice {
    Discussione creaDiscussione(Discussione discussione) ;
    Discussione modificaDiscussione(Discussione discussione, int idDiscussione) ;
    Discussione cancellaDiscussione(int idDiscussione) ;
    List<Discussione> ottieniDiscussioniDaCategoria(Categoria categoria);

    Commento creaCommento(Commento commento) ;
    Commento modificaCommento(Commento commento) ;
    void rimuoviCommento(Commento commento) ;


}
