package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentoDAO {

    public Commento doRetrieveById(int id) {
        LettoreDAO lettoreDAO = new LettoreDAO() ;
        DiscussioneDAO discussioneDAO = new DiscussioneDAO();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idCommento, corpo, discussione, dataCommento, autore" +
                            "  FROM formulaonlinedb.commento c  " +
                            " WHERE c.idCommento=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.getInt(1);
                Commento commento = new Commento();
                commento.setIdCommento(rs.getInt(1));
                commento.setCorpo(rs.getString(2));

                Discussione discussione = discussioneDAO.doRetrieveById(rs.getInt(3));
                commento.setDiscussione(discussione);

                commento.setDataCommento(rs.getDate(4));

                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                commento.setAutore(lettore);

                return commento;
            }
            return null;

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
