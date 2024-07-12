package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscussioneDAO {
    private CategoriaDAO categoriaDAO;
    private LettoreDAO lettoreDAO;

    public Discussione doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idDiscussione, numeroCommenti, categoria, titolo, autore" +
                            "  FROM formulaonlinedb.discussione c  " +
                            " WHERE c.idDiscussione=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Discussione discussione = new Discussione();
                discussione.setIdDiscussione(rs.getInt(1));
                discussione.setNumeroCommenti(rs.getInt(2));
                categoriaDAO = new CategoriaDAO();
                Categoria categoria = categoriaDAO.doRetrieveById(rs.getInt(3));
                discussione.setCategoria(categoria);

                discussione.setTitolo(rs.getString(4));
                lettoreDAO = new LettoreDAO();
                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                discussione.setLettore(lettore);
                return discussione;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Discussione> doRetrieveAll() {
        List<Discussione> discussioneList = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idDiscussione, numeroCommenti, categoria, titolo, autore" +
                            "  FROM formulaonlinedb.discussione d");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Discussione discussione = new Discussione();
                discussione.setIdDiscussione(rs.getInt(1));
                discussione.setNumeroCommenti(rs.getInt(2));
                categoriaDAO = new CategoriaDAO();
                Categoria categoria = categoriaDAO.doRetrieveById(rs.getInt(3));
                discussione.setCategoria(categoria);

                discussione.setTitolo(rs.getString(4));
                lettoreDAO = new LettoreDAO();

                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                discussione.setLettore(lettore);

                discussioneList.add(discussione);

            }
            return discussioneList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Discussione> doRetrieveByCategoria(int idCategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idDiscussione, numeroCommenti, categoria, titolo, autore" +
                            "  FROM formulaonlinedb.discussione d" +
                            " WHERE d.categoria = ?");

            ps.setInt(1,idCategoria);
            ResultSet rs = ps.executeQuery();

            List<Discussione> discussioneList = new ArrayList<>();
            while (rs.next()) {
                Discussione discussione = new Discussione();
                discussione.setIdDiscussione(rs.getInt(1));
                discussione.setNumeroCommenti(rs.getInt(2));
                categoriaDAO = new CategoriaDAO();

                Categoria categoria  = categoriaDAO.doRetrieveById(rs.getInt(3));
                discussione.setCategoria(categoria);

                discussione.setTitolo(rs.getString(4));
                lettoreDAO = new LettoreDAO();

                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                discussione.setLettore(lettore);

                discussioneList.add(discussione);

            }
            return discussioneList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Discussione doSave(Discussione discussione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.discussione (numeroCommenti, categoria, titolo, autore)" +
                            "  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, discussione.getNumeroCommenti());
            ps.setInt(2, discussione.getCategoria().getIdCategoria());
            ps.setString(3,discussione.getTitolo());
            ps.setInt(4, discussione.getLettore().getIdLettore());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getResultSet();
            if(rs.next()){
                discussione.setIdDiscussione(rs.getInt("idDiscussione"));
                return discussione;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Discussione doSave(Discussione discussione, Commento commento) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.commento (corpo, discussione, dataCommento, autore)" +
                            "  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, commento.getCorpo());
            ps.setInt(2, commento.getDiscussione().getIdDiscussione());
            ps.setDate(3, (Date) commento.getDataCommento());
            ps.setInt(4, commento.getAutore().getIdLettore());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.discussione (numeroCommenti, categoria, titolo, autore)" +
                            "  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, discussione.getNumeroCommenti());
            ps.setInt(2, discussione.getCategoria().getIdCategoria());
            ps.setString(3,discussione.getTitolo());
            ps.setInt(4, discussione.getLettore().getIdLettore());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getResultSet();
            if(rs.next()){
                discussione.setIdDiscussione(rs.getInt("idDiscussione"));
                return discussione;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Discussione doSave(String titolo, int idLettore, int idCategoria){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.discussione (categoria, titolo, autore)" +
                            "  VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, idCategoria);
            ps.setString(2, titolo);
            ps.setInt(3, idLettore);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            CategoriaDAO cd = new CategoriaDAO();
            LettoreDAO ld = new LettoreDAO();
            Discussione discussione= new Discussione(rs.getInt(1), 0,
                    cd.doRetrieveById(idCategoria),  titolo, ld.doRetrieveById(idLettore));
            return discussione;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Discussione doUpdate(Discussione discussione, int idDiscussione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.discussione "+
                            " SET categoria = ?, titolo = ?" +
                            "  WHERE idDiscussione=? ");

            ps.setInt(1, discussione.getCategoria().getIdCategoria());
            ps.setString(2, discussione.getTitolo());
            ps.setInt(3, idDiscussione);
            ps.executeUpdate();

            return discussione;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int idDiscussione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM formulaonlinedb.discussione WHERE idDiscussione=?");
            ps.setInt(1, idDiscussione);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Discussione> doRetrieveByTitolo(String titolo) {
        try (Connection con = ConPool.getConnection()) {
            titolo = "%" + titolo +"%";
            PreparedStatement ps =
                    con.prepareStatement("SELECT idDiscussione, numeroCommenti, categoria, titolo, autore" +
                            "  FROM formulaonlinedb.discussione d  " +
                            " WHERE d.titolo LIKE ?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            List<Discussione> lista = new ArrayList<>();
            while (rs.next()) {
                rs.getInt(1);
                Discussione discussione = new Discussione();
                discussione.setIdDiscussione(rs.getInt(1));
                discussione.setNumeroCommenti(rs.getInt(2));
                categoriaDAO = new CategoriaDAO();
                Categoria categoria = categoriaDAO.doRetrieveById(rs.getInt(3));
                discussione.setCategoria(categoria);
                discussione.setTitolo(rs.getString(4));
                lettoreDAO = new LettoreDAO();
                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                discussione.setLettore(lettore);
                lista.add(discussione);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Discussione> doRetrieveRecenti() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT distinct d.idDiscussione, d.numeroCommenti, d.categoria, d.titolo, d.autore " +
                            "FROM formulaonlinedb.discussione d, formulaonlinedb.commento c " +
                            "WHERE c.discussione = d.idDiscussione ORDER BY c.dataCommento LIMIT 5;");
            ResultSet rs = ps.executeQuery();
            List<Discussione> lista = new ArrayList<>();
            while (rs.next()) {
                rs.getInt(1);
                Discussione discussione = new Discussione();
                discussione.setIdDiscussione(rs.getInt(1));
                discussione.setNumeroCommenti(rs.getInt(2));
                categoriaDAO = new CategoriaDAO();
                Categoria categoria = categoriaDAO.doRetrieveById(rs.getInt(3));
                discussione.setCategoria(categoria);
                discussione.setTitolo(rs.getString(4));
                lettoreDAO = new LettoreDAO();
                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                discussione.setLettore(lettore);
                lista.add(discussione);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doAggiungiNComm(int idDiscussione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.discussione "+
                            " SET numeroCommenti = numeroCommenti + 1" +
                            "  WHERE idDiscussione=?");
            ps.setInt(1, idDiscussione);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doRimuoviNComm(int idDiscussione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.discussione "+
                            " SET numeroCommenti = numeroCommenti - 1" +
                            "  WHERE idDiscussione=?");
            ps.setInt(1, idDiscussione);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
