package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.model.dao.CategoriaDAO;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Discussione;

public class GestioneCategoriaDiscussioneImplementazione implements GestioneCategoriaDiscussioneService {
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    @Override
    public Categoria creaCategoriaDiscussione(Categoria categoria) {
        categoriaDAO.doSave(categoria);
        return categoria;
    }

    @Override
    public Categoria modificaCategoriaDiscussione(Categoria categoria, int idCategoria) {
        categoriaDAO.doUpdate(categoria, idCategoria);
        return categoria;
    }

    @Override
    public void cancellaCategoriaDiscussione(int idCategoria) {
        categoriaDAO.doDeleteAlternativo(idCategoria);
    }
}
