package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.model.dao.CategoriaDAO;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

public class GestioneCategoriaDiscussioneImplementazione implements GestioneCategoriaDiscussioneService {
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private LettoreDAO lettoreDAO = new LettoreDAO();
    @Override
    public Categoria creaCategoriaDiscussione(String nome, String descrizione, int categoriaPadreId, int autore) {
        Lettore lettore = lettoreDAO.doRetrieveById(autore);
        Categoria categoriaPadre = categoriaDAO.doRetrieveById(categoriaPadreId) ;
        return categoriaDAO.doSave(new Categoria(nome, descrizione, categoriaPadre, lettore));
    }

    @Override
    public Categoria modificaCategoriaDiscussione(int idCategoria, String nome, String descrizione, int categoriaPadreId) {
        Categoria categoria = categoriaDAO.doRetrieveById(idCategoria) ;
        categoria.setNome(nome);
        categoria.setDescrizione(descrizione);
        Categoria categoriaPadre = categoriaDAO.doRetrieveById(categoriaPadreId) ;
        categoria.setCategoriaPadre(categoriaPadre);

        return categoriaDAO.doUpdate(categoria, idCategoria);

    }

    @Override
    public void eliminaCategoria(int idCategoria) {
        categoriaDAO.doDeleteAlternativo(idCategoria);
    }

    @Override
    public Categoria retrieveById(int idCategoria) {
        return categoriaDAO.doRetrieveById(idCategoria);
    }
}
