package unisa.it.formulaonline.ricerca.service;

import unisa.it.formulaonline.model.dao.DiscussioneDAO;
import unisa.it.formulaonline.model.entity.Discussione;

import java.util.List;

public class RicercaServiceImpl implements RicercaService{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Discussione> ricerca(String titolo) {
        DiscussioneDAO dd = new DiscussioneDAO();
        return dd.doRetrieveByTitolo(titolo);
    }
}
