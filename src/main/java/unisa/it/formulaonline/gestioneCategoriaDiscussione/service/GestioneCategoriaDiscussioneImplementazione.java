package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.autenticazione.service.LettoreService;
import unisa.it.formulaonline.autenticazione.service.LettoreServiceImpl;
import unisa.it.formulaonline.model.dao.CategoriaDAO;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.List;

public class GestioneCategoriaDiscussioneImplementazione implements GestioneCategoriaDiscussioneService {
    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria creaCategoriaDiscussione(String nome, String descrizione, int categoriaPadreId, int creatore) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.doSave(nome, descrizione, categoriaPadreId, creatore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria modificaCategoriaDiscussione(int idCategoria, String nome, String descrizione, int categoriaPadreId) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        Categoria categoria = categoriaDAO.doRetrieveById(idCategoria) ;
        categoria.setNome(nome);
        categoria.setDescrizione(descrizione);
        /*Se è maggiore di 0 allora è relativa ad una categoria esistente*/
        if(categoriaPadreId>0){
            Categoria categoriaPadre = categoriaDAO.doRetrieveById(categoriaPadreId) ;
            categoria.setCategoriaPadre(categoriaPadre);
        }
        else {
            categoria.setCategoriaPadre(null);
        }

        return categoriaDAO.doUpdate(categoria, idCategoria);

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

    @Override
    public List<Categoria> ottieniTutteCategorie() {
        CategoriaDAO cd = new CategoriaDAO();
        return cd.doRetrieveAll();
    }
}
