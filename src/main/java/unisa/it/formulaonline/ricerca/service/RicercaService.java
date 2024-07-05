package unisa.it.formulaonline.ricerca.service;

import unisa.it.formulaonline.model.entity.Discussione;

import java.util.List;

/**
 * Espone i metodi che permettono la ricerca delle discussioni
 */
public interface RicercaService {
    /**
     * Data una stringa restituisce la lista delle discussioni che comprende quella stringa nel titolo
     * @param titolo la stringa o sottostringa da dover cercare
     * @return la lista di discussioni come risultato della ricerca, null se non ha prodotto risultati
     */
    List<Discussione> ricerca(String titolo);
}
