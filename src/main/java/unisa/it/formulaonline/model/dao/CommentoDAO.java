package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la gestione dei commenti nel database.
 */
public class CommentoDAO {

    /**
     * Metodo per recuperare un commento partendo dal suo identificativo
     * @param id identificativo del commento
     * @return il commento relativo all'id, null se il commento non esiste
     */
    public Commento doRetrieveById(int id) {
        LettoreDAO lettoreDAO = new LettoreDAO() ;
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idCommento, corpo, discussione, dataCommento, autore" +
                            "  FROM formulaonlinedb.commento c" +
                            " WHERE c.idCommento=? ORDER BY dataCommento");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Commento commento = new Commento();
                commento.setIdCommento(rs.getInt(1));
                commento.setCorpo(rs.getString(2));

                Discussione discussione = discussioneDAO.doRetrieveById(rs.getInt(3));
                commento.setDiscussione(discussione);

                commento.setDataCommento(rs.getTimestamp(4));

                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                commento.setAutore(lettore);

                return commento;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che recupera la lista dei commenti in ordine cronologico
     * @param idDiscussione id della discussione di cui si vogliono ottenere i commenti
     * @return la lista dei commenti
     */
    public List<Commento> doRetrieveByDiscussione(int idDiscussione) {
        LettoreDAO lettoreDAO = new LettoreDAO() ;
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idCommento, corpo, discussione, dataCommento, autore" +
                            "  FROM formulaonlinedb.commento c  " +
                            " WHERE c.discussione=? ORDER BY dataCommento");
            ps.setInt(1, idDiscussione);
            ResultSet rs = ps.executeQuery();
            List<Commento> commenti = new ArrayList<>();
            while (rs.next()) {
                Commento commento = new Commento();
                commento.setIdCommento(rs.getInt(1));
                commento.setCorpo(rs.getString(2));

                Discussione discussione = discussioneDAO.doRetrieveById(rs.getInt(3));
                commento.setDiscussione(discussione);

                commento.setDataCommento(rs.getTimestamp(4));

                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                commento.setAutore(lettore);
                commenti.add(commento);
            }
            return commenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Commento> doRetrieveAll() {
        LettoreDAO lettoreDAO = new LettoreDAO() ;
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();
        List<Commento> commentoList = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT c.idCommento, c.corpo,c.discussione, c.dataCommento, c.autore" +
                            "  FROM formulaonlinedb.commento c");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Commento commento = new Commento();
                commento.setIdCommento(rs.getInt(1));
                commento.setCorpo(rs.getString(2));

                Discussione discussione = discussioneDAO.doRetrieveById(rs.getInt(3));
                commento.setDiscussione(discussione);
                commento.setDataCommento(rs.getDate(4));

                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                commento.setAutore(lettore);

                commentoList.add(commento);

            }
            return commentoList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metodo per salvare un nuovo commento in una discusssione
     * @param idDiscussione id della discussione a cui aggiungere il commento
     * @param idLettore id del lettore che ha scritto il commento
     * @param corpo contentuto del commento
     * @return il commento creato
     */
    public Commento doSave(int idDiscussione, int idLettore, String corpo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.commento (corpo,discussione,autore)" +
                            "  VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, corpo);
            ps.setInt(2, idDiscussione);
            ps.setInt(3, idLettore);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return doRetrieveById(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Commento doSave(Commento commento) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.commento (corpo,discussione,dataCommento,autore)" +
                            "  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, commento.getCorpo());
            ps.setInt(2, commento.getDiscussione().getIdDiscussione());
            ps.setDate(3, (Date) commento.getDataCommento());
            ps.setInt(4, commento.getAutore().getIdLettore());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            return commento;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Commento doUpdate(Commento commento, int idCommento) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.commento "+
                            " SET corpo = ? " +
                            "  WHERE idCommento=? ");

            ps.setString(1, commento.getCorpo());
            ps.setInt(2, idCommento);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
            commento.setIdCommento(idCommento);
            return commento;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateAutore(int idAutore, int idNewAutore){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.commento "+
                            " SET autore = ? " +
                            "  WHERE autore = ?");
            ps.setInt(1, idNewAutore);
            ps.setInt(2, idAutore);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per eliminare un commento
     * @param idCommento id del commento da eliminare
     */
    public void doDelete(int idCommento) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM formulaonlinedb.commento WHERE idCommento=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idCommento);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per eliminare tutti i commenti fatti da un utente
     * @param idAutore id dell'autore di cui devono essere eliminati i commenti
     */
    public void doDeleteByAutore(int idAutore){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM formulaonlinedb.commento WHERE autore=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idAutore);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
