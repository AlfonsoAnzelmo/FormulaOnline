package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.autenticazione.service.LettoreService;
import unisa.it.formulaonline.autenticazione.service.LettoreServiceImpl;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneCategoriaDiscussione.service.GestioneCategoriaDiscussioneService;
import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Lettore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta una categoria a cui appartengono le discussioni
 */
public class CategoriaDAO {

    private LettoreDAO ld;

    /**
     * Metodo per ottenere una categoria dato il suo identificativo
     * @param id identificativo della categoria
     * @return la categoria se è stata trovata, null altrimenti
     */
    public Categoria doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT c.idCategoria, c.nome,c.descrizione, c.categoriaPadre, c.creatore" +
                            "  FROM formulaonlinedb.categoria c  " +
                            " WHERE c.idCategoria=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.getInt(1);
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNome(rs.getString(2));
                categoria.setDescrizione(rs.getString(3));

                Categoria categoriaPadre = doRetrieveById(rs.getInt(4));
                categoria.setCategoriaPadre(categoriaPadre);

                ld= new LettoreDAO();
                Lettore lettore = ld.doRetrieveById(rs.getInt(5));
                categoria.setCreatore(lettore);

                return categoria;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per recuperare tutte le categorie
     * @return una lista con tutte le categoria
     */
    public List<Categoria> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT c.idCategoria, c.nome,c.descrizione, c.categoriaPadre, c.creatore" +
                            "  FROM  formulaonlinedb.categoria c");

            ResultSet rs = ps.executeQuery();
            List<Categoria> categoriaList = new ArrayList<>();
            ld= new LettoreDAO();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNome(rs.getString(2));
                categoria.setDescrizione(rs.getString(3));

                Categoria categoriaPadre = doRetrieveById(rs.getInt(4));
                categoria.setCategoriaPadre(categoriaPadre);

                Lettore lettore = ld.doRetrieveById(rs.getInt(5));
                categoria.setCreatore(lettore);
                categoriaList.add(categoria);
            }
            return categoriaList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Resitituisce tutte le sottocategorie relative alla categoria padre
     * @param idCategoria se 0 considera viene considerato null
     * @return la lista delle sottocategorie
     */
    public List<Categoria> doRetrieveByPadre(int idCategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT c.idCategoria, c.nome, c.descrizione, c.categoriaPadre, c.creatore" +
                            "  FROM  formulaonlinedb.categoria c WHERE categoriaPadre=?");
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            List<Categoria> categoriaList = new ArrayList<>();
            ld= new LettoreDAO();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNome(rs.getString(2));
                categoria.setDescrizione(rs.getString(3));

                Categoria categoriaPadre = doRetrieveById(rs.getInt(4));
                categoria.setCategoriaPadre(categoriaPadre);

                Lettore lettore = ld.doRetrieveById(rs.getInt(5));
                categoria.setCreatore(lettore);
                categoriaList.add(categoria);
            }
            return categoriaList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Resitituisce tutte le categorie principali
     * @return la lista delle categorie senza una categoria padre
     */
    public List<Categoria> doRetrievePrincipali() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT c.idCategoria, c.nome, c.descrizione, c.categoriaPadre, c.creatore" +
                            "  FROM  formulaonlinedb.categoria c WHERE categoriaPadre is null");
            ResultSet rs = ps.executeQuery();
            List<Categoria> categoriaList = new ArrayList<>();
            ld= new LettoreDAO();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNome(rs.getString(2));
                categoria.setDescrizione(rs.getString(3));

                Categoria categoriaPadre = doRetrieveById(rs.getInt(4));
                categoria.setCategoriaPadre(categoriaPadre);

                ld= new LettoreDAO();
                Lettore lettore = ld.doRetrieveById(rs.getInt(5));
                categoria.setCreatore(lettore);
                categoriaList.add(categoria);
            }
            return categoriaList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per salvare una nuova categoria nel database
     * NON UTILIZZATA
     * @param categoria
     * @return
     */
    public Categoria doSave(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.categoria (nome,descrizione,categoriaPadre,creatore)" +
                            "  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescrizione());
            if(categoria.getCategoriaPadre()!=null) {
                ps.setInt(3, categoria.getCategoriaPadre().getIdCategoria());
            }
            else{
                ps.setNull(3, Types.INTEGER);
            }
            ps.setInt(4, categoria.getCreatore().getIdLettore());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            categoria.setIdCategoria(rs.getInt(1));
            return categoria;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per salvare una nuova categoria nel database
     * @param nome della nuova categoria
     * @param descrizione della categoria
     * @param categoriaPadre id della categoria a cui dovrà appartenere, 0 se non appartiene a nessuna
     * @param creatore id del lettore che l'ha creata
     * @return la categoria appena creata
     */
    public Categoria doSave(String nome, String descrizione, int categoriaPadre, int creatore) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.categoria (nome,descrizione,categoriaPadre,creatore)" +
                            "  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nome);
            ps.setString(2, descrizione);
            if(categoriaPadre!=0) {
                ps.setInt(3, categoriaPadre);
            }
            else{
                ps.setNull(3, Types.INTEGER);
            }
            ps.setInt(4, creatore);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            LettoreDAO ld = new LettoreDAO();
            ResultSet rs = ps.getGeneratedKeys();
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getInt(1));
            categoria.setDescrizione(descrizione);
            categoria.setCreatore(ld.doRetrieveById(creatore));
            categoria.setCategoriaPadre(doRetrieveById(categoriaPadre));
            return categoria;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria doUpdate(Categoria categoria, int idCategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.categoria "+
                            " SET creatore = ?, categoriaPadre = ? , nome = ? ,descrizione = ? " +
                            "  WHERE idCategoria=? ");

            ps.setInt(1, categoria.getCreatore().getIdLettore());
            if(categoria.getCategoriaPadre()!=null) {
                ps.setInt(2, categoria.getCategoriaPadre().getIdCategoria());
            }
            else{
                ps.setNull(2, Types.INTEGER);
            }
            ps.setString(3, categoria.getNome());
            ps.setString(4, categoria.getDescrizione());
            ps.setInt(5, idCategoria);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            return categoria;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che sposta le sottocategorie di una categoria in "senza categoria"
     * @param idCategoriaPadre id della categoria da cui spostare le sottocategorie
     */
    public void doSetDefaultCategoriaPadre(int idCategoriaPadre) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.categoria "+
                            " SET categoriaPadre = 1 " +
                            "  WHERE categoriaPadre=? ");

            ps.setInt(1, idCategoriaPadre);
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  elimina la categoria selezionata e le sottocategorie
     * @param idCategoria id della ca
     */
    public void doDelete(int idCategoria){

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM formulaonlinedb.categoria WHERE idCategoria=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idCategoria);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * elimina la categoria selezionata e assegna alle sottocategorie "senza categoria" come categoria padre
     * @param idCategoria id della categoria da eliminare, l'id non può essere relativo alla categoria
     *                    "senza categoria"
     */
    public void doDeleteAlternativo(int idCategoria){

        if(idCategoria!=1){
            doSetDefaultCategoriaPadre(idCategoria);
            try (Connection con = ConPool.getConnection()) {
                /*elimina la categoria scelta*/
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM formulaonlinedb.categoria WHERE idCategoria=?");
                ps.setInt(1, idCategoria);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
