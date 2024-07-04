package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.model.entity.Segnalazione;

import java.sql.*;
import java.util.ArrayList;

public class SegnalazioneDAO {
    private LettoreDAO ld;
    private CommentoDAO cd;

    public Segnalazione doRetrieveByLettore(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT lettore, moderatore, commento, corpo FROM segnalazione WHERE lettore=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Segnalazione s = new Segnalazione();
                ld = new LettoreDAO();
                cd = new CommentoDAO();
                s.setAutore(ld.doRetrieveById(rs.getInt(1)));
                if (rs.getInt(2)!=0)
                    s.setGestita(ld.doRetrieveById(rs.getInt(2)));
                s.setCommento(cd.doRetrieveById(rs.getInt(3)));
                s.setCorpo(rs.getString(4));
                return s;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Segnalazione> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT lettore, moderatore, commento, corpo FROM segnalazione");
            ResultSet rs = ps.executeQuery();
            ArrayList<Segnalazione> segnalazioni = new ArrayList<>();
            Segnalazione s;
            ld = new LettoreDAO();
            cd = new CommentoDAO();
            while (rs.next()) {
                s = new Segnalazione();
                s.setAutore(ld.doRetrieveById(rs.getInt(1)));
                if (rs.getInt(2)!=0)
                    s.setGestita(ld.doRetrieveById(rs.getInt(2)));
                s.setCommento(cd.doRetrieveById(rs.getInt(3)));
                s.setCorpo(rs.getString(4));
                segnalazioni.add(s);
            }
            return segnalazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Segnalazione> doRetrieveAperte(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT lettore, commento, corpo FROM segnalazione WHERE moderatore is null");
            ResultSet rs = ps.executeQuery();
            ArrayList<Segnalazione> segnalazioni = new ArrayList<>();
            Segnalazione s;
            while (rs.next()) {
                s = new Segnalazione();
                s.setAutore(ld.doRetrieveById(rs.getInt(1)));
                if (rs.getInt(2)!=0)
                    s.setGestita(ld.doRetrieveById(rs.getInt(2)));
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
                    con.prepareStatement("INSERT INTO segnalazione (lettore, moderatore, commento, corpo) value(?,?,?,?)");
            if (ps.executeUpdate()!=1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    public void doDelete(int lettore, int commento){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM segnalazione WHERE lettore=? and commento=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lettore);
            ps.setInt(2, commento);
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
            String query = "update Segnalazione set lettore='" + s.getAutore().getIdLettore() + "', moderatore=" + s.getGestita().getIdLettore()
                    + ", commento='" + s.getCommento().getIdCommento() + "', corpo='" + s.getCorpo()
                    + "' where lettore=" + s.getAutore() + "and commento='"+ s.getCommento().getIdCommento() +"';";
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean doExists(int lettore, int commento){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT lettore, commento FROM segnalazione WHERE lettore=? AND commento=?");
            ps.setInt(1, lettore);
            ps.setInt(2, commento);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
