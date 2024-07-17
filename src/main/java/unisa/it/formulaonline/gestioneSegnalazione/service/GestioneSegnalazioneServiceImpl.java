package unisa.it.formulaonline.gestioneSegnalazione.service;

import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.dao.SegnalazioneDAO;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.model.entity.Segnalazione;

import java.sql.Date;
import java.util.List;

public class GestioneSegnalazioneServiceImpl implements GestioneSegnalazioneService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Segnalazione creaSegnalazione(int idCommento, int idAutore, String corpo) {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
        if (corpo.length() > 5 && corpo.length() <= 250 && idCommento != 0) {
            GestioneDiscussioneService discussioneService = new GestioneDiscussioneImplementazione();

            Segnalazione s = segnalazioneDAO.doSave(idAutore, idCommento, corpo);
            Commento c = discussioneService.ottieniCommentoDaId(idCommento);
            s.setCommento(c);
            return s;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sospendiLettore(int idSegnalazione, Date dataFineSospensione) {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
        LettoreDAO lettoreDAO = new LettoreDAO();
        GestioneDiscussioneService discussioneService = new GestioneDiscussioneImplementazione();

        Segnalazione segnalazione = segnalazioneDAO.doRetrieveById(idSegnalazione);
        Lettore lettore = segnalazione.getCommento().getAutore();
        lettore.setDataFineSospensione(dataFineSospensione);
        lettoreDAO.doUpdate(lettore.getIdLettore(), lettore.getEmail(), lettore.getPassword(),
                lettore.getNickname(), lettore.getScuderiaPref(), lettore.getModeratore(), dataFineSospensione);
        segnalazioneDAO.doDelete(segnalazione.getIdSegnalazione());
        discussioneService.rimuoviCommento(segnalazione.getCommento().getIdCommento(), segnalazione.getCommento().
                getDiscussione().getIdDiscussione());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminaSegnalazione(int idSegnalazione) {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();

        segnalazioneDAO.doDelete(idSegnalazione);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Segnalazione> ottieniSegnalazioni() {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();

        return segnalazioneDAO.doRetrieveAll();
    }

    public Segnalazione ottieniSegnalazioneDaId(int idSegnalazione) {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();

        return segnalazioneDAO.doRetrieveById(idSegnalazione);
    }
}
