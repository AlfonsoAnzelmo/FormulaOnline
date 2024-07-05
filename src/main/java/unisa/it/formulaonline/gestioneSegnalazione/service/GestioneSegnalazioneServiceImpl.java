package unisa.it.formulaonline.gestioneSegnalazione.service;

import unisa.it.formulaonline.model.dao.CommentoDAO;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.dao.SegnalazioneDAO;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.model.entity.Segnalazione;

import java.sql.Date;
import java.util.List;

public class GestioneSegnalazioneServiceImpl implements GestioneSegnalazioneService{

    private SegnalazioneDAO sd = new SegnalazioneDAO();
    /**
     * {@inheritDoc}
     */
    @Override
    public Segnalazione creaSegnalazione(Segnalazione segnalazione) {
        return sd.doSave(segnalazione);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sospendiLettore(Segnalazione segnalazione, Date dataFineSospensione) {
        LettoreDAO ld = new LettoreDAO();
        Lettore l = segnalazione.getCommento().getAutore();
        l.setDataFineSospensione(dataFineSospensione);
        ld.doUpdate(l, l.getIdLettore());
        sd.doUpdate(segnalazione);
        /*dovrebbe anche cancellare il commento mo che ci penso ed a cascata tutte le segnalazioni collegate
        CommentoDAO cd = new CommentoDAO();
        cd.doDelete(segnalazione.getCommento().getIdCommento());
        */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminaSegnalazione(Segnalazione segnalazione) {
        sd.doDelete(segnalazione.getAutore().getIdLettore(), segnalazione.getCommento().getIdCommento());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Segnalazione> visualizzaSegnalazioni() {
        return null;
    }

}
