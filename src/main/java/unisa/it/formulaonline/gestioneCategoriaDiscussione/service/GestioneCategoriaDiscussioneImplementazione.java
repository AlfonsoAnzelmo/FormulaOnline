package unisa.it.formulaonline.gestioneCategoriaDiscussione.service;

import unisa.it.formulaonline.autenticazione.service.LettoreService;
import unisa.it.formulaonline.autenticazione.service.LettoreServiceImpl;
import unisa.it.formulaonline.model.dao.CategoriaDAO;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Lettore;

public class GestioneCategoriaDiscussioneImplementazione implements GestioneCategoriaDiscussioneService {
    @Override
    public Categoria creaCategoriaDiscussione(String nome, String descrizione, int categoriaPadreId, int autore) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        LettoreService lettoreService = new LettoreServiceImpl();

        Lettore lettore = lettoreService.ottieniLettoreDaId(autore);
        Categoria categoriaPadre = categoriaDAO.doRetrieveById(categoriaPadreId) ;
        return categoriaDAO.doSave(new Categoria(nome, descrizione, categoriaPadre, lettore));
    }

    @Override
    public Categoria modificaCategoriaDiscussione(int idCategoria, String nome, String descrizione, int categoriaPadreId) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        LettoreService lettoreService = new LettoreServiceImpl();

        Categoria categoria = categoriaDAO.doRetrieveById(idCategoria) ;
        categoria.setNome(nome);
        categoria.setDescrizione(descrizione);
        Categoria categoriaPadre = categoriaDAO.doRetrieveById(categoriaPadreId) ;
        categoria.setCategoriaPadre(categoriaPadre);

        return categoriaDAO.doUpdate(categoria, idCategoria);

    }

    @Override
    public void eliminaCategoria(int idCategoria) {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.doDeleteAlternativo(idCategoria);
    }

    @Override
    public Categoria retrieveById(int idCategoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.doRetrieveById(idCategoria);
    }

    @Override
    public Categoria ottienieCategoriaDaId(int idCategoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.doRetrieveById(idCategoria);}
}
