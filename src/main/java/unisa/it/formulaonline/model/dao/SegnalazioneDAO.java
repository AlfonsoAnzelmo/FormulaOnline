package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.model.entity.Segnalazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la gestione delle segnalazioni nel database.
 */
public class SegnalazioneDAO {
    private LettoreDAO ld;
    private CommentoDAO cd;

    public List<Segnalazione> doRetrieveByLettore(int id){
        try (Connection con = ConPool.getConnection()) {
            ld = new LettoreDAO();
            cd = new CommentoDAO();
            PreparedStatement ps =
                    con.prepareStatement("SELECT idSegnalazione, lettore, commento, corpo " +
                            "FROM formulaonlinedb.segnalazione " +
                            "WHERE lettore=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Segnalazione> listaS = new ArrayList<>();
            while (rs.next()) {
                Segnalazione s = new Segnalazione();
                s.setIdSegnalazione(rs.getInt(1));
                s.setAutore(ld.doRetrieveById(rs.getInt(2)));
                s.setCommento(cd.doRetrieveById(rs.getInt(3)));
                s.setCorpo(rs.getString(4));
                listaS.add(s);
            }
            return listaS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per recuperare una segnalazione partendo da suo identificativo
     * @param id della segnalazione
     * @return la segnalazione completa se è stata trovata, null altrimenti
     */
    public Segnalazione doRetrieveById(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idSegnalazione, lettore, commento, corpo " +
                            "FROM formulaonlinedb.segnalazione " +
                            "WHERE idSegnalazione=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Segnalazione s = new Segnalazione();
                ld = new LettoreDAO();
                cd = new CommentoDAO();
                s.setIdSegnalazione(rs.getInt(1));
                s.setAutore(ld.doRetrieveById(rs.getInt(2)));
                s.setCommento(cd.doRetrieveById(rs.getInt(3)));
                s.setCorpo(rs.getString(4));
                return s;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per recuperare tutte le segnalazioni presenti nel database
     * @return la lista di tutte le segnalazioni, la lista sarà vuota se non ne esistono
     */
    public ArrayList<Segnalazione> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idSegnalazione, lettore, commento, corpo " +
                            "FROM formulaonlinedb.segnalazione");
            ld = new LettoreDAO();
            cd = new CommentoDAO();
            ArrayList<Segnalazione> segnalazioni = new ArrayList<>();
            Segnalazione s;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s = new Segnalazione();
                s.setIdSegnalazione(rs.getInt(1));
                s.setAutore(ld.doRetrieveById(rs.getInt(2)));
                s.setCommento(cd.doRetrieveById(rs.getInt(3)));
                s.setCorpo(rs.getString(4));
                segnalazioni.add(s);
            }
            return segnalazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Segnalazione doSave(Segnalazione s){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO formulaonlinedb.segnalazione " +
                            "(idSegnalazione, lettore, commento, corpo) value(?,?,?,?)");
            if (ps.executeUpdate()!=1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    /**
     * Metodo per salvare una nuova segnalazione nel database
     * @param idLettore che ha inviato la segnalazione
     * @param idCommento del commento segnalato
     * @param corpo della segnalazione
     * @return la segnalazione se la creazione è andata a buon fine
     */
    public Segnalazione doSave(int idLettore, int idCommento, String corpo){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO formulaonlinedb.segnalazione " +
                            "(lettore, commento, corpo) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, idLettore);
            ps.setInt(2, idCommento);
            ps.setString(  3, corpo);

            if (ps.executeUpdate()!=1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            Segnalazione s = new Segnalazione();
            rs.next();
            s.setIdSegnalazione(rs.getInt(1));

            return s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per eliminare una segnalazione
     * @param idSegnalazione della segnalazione da eliminare
     */
    public void doDelete(int idSegnalazione){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM formulaonlinedb.segnalazione WHERE idSegnalazione=?");
            ps.setInt(1, idSegnalazione);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void doUpdate(Segnalazione s){
        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "update formulaonlinedb.segnalazione " +
                    "where idSegnalazione=" + s.getIdSegnalazione() +
                    " set lettore=" + s.getAutore().getIdLettore() +
                    ", commento=" + s.getCommento().getIdCommento() + ", corpo='" + s.getCorpo() + "'";
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
