package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.model.dao.CategoriaDAO;
import unisa.it.formulaonline.model.entity.Categoria;

import java.util.List;

public class GestioneCategoriaDiscussioneImplementazione implements GestioneCategoriaDiscussioneService {
    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria creaCategoriaDiscussione(String nome, String descrizione, int categoriaPadreId, int creatore) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        if (creatore != 0 && nome != null && descrizione != null && categoriaPadreId != 0 &&
                nome.length() >= 5 && nome.length() <= 50 && descrizione.length() <= 300)
            return categoriaDAO.doSave(nome, descrizione, categoriaPadreId, creatore);

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria modificaCategoriaDiscussione(int idCategoria, String nome, String descrizione, int categoriaPadreId) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        Categoria categoria = categoriaDAO.doRetrieveById(idCategoria) ;

        if ( nome != null && descrizione != null && categoriaPadreId != 0 &&
                nome.length() >= 5 && nome.length() <= 50 && descrizione.length() <= 300) {
            categoria.setNome(nome);
            categoria.setDescrizione(descrizione);

            Categoria categoriaPadre = categoriaDAO.doRetrieveById(categoriaPadreId);
            categoria.setCategoriaPadre(categoriaPadre);

            return categoriaDAO.doUpdate(categoria, idCategoria);
        }
        return null;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminaCategoria(int idCategoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.doDeleteAlternativo(idCategoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria ottieniCategoriaDaId(int idCategoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.doRetrieveById(idCategoria);}

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Categoria> ottieniCategoriePrincipali() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.doRetrievePrincipali();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Categoria> ottieniSottocategorie(int idCategoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.doRetrieveByPadre(idCategoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Categoria> ottieniTutteCategorie() {
        CategoriaDAO cd = new CategoriaDAO();
        return cd.doRetrieveAll();
    }
}
