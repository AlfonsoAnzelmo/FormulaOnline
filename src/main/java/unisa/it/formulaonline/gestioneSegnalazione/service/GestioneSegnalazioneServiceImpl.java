package unisa.it.formulaonline.gestioneSegnalazione.service;

import unisa.it.formulaonline.autenticazione.service.LettoreService;
import unisa.it.formulaonline.autenticazione.service.LettoreServiceImpl;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.dao.SegnalazioneDAO;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.model.entity.Segnalazione;

import java.sql.Date;
import java.util.List;

public class GestioneSegnalazioneServiceImpl implements GestioneSegnalazioneService{

    private SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
    LettoreDAO lettoreDAO = new LettoreDAO();
    GestioneDiscussioneService discussioneService = new GestioneDiscussioneImplementazione();
    /**
     * {@inheritDoc}
     */
    @Override
    public Segnalazione creaSegnalazione(int idCommento, int idAutore, String corpo) {
        Segnalazione s = segnalazioneDAO.doSave(idCommento, idAutore, corpo);
        Commento c = discussioneService.ottieniCommentoDaId(idCommento);
        s.setCommento(c);
        return s;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sospendiLettore(int idSegnalazione, Date dataFineSospensione) {
        Segnalazione segnalazione = segnalazioneDAO.doRetrieveById(idSegnalazione);
//        lettoreService.ottieniLettoreDaId(segnalazione.getAutore().getIdLettore());
        Lettore lettore = segnalazione.getCommento().getAutore();
        lettore.setDataFineSospensione(dataFineSospensione);
//        lettoreService.aggiornaLettore(lettore.getIdLettore(), );
        lettoreDAO.doUpdate(lettore.getIdLettore(), lettore.getEmail(), lettore.getPassword(),
                lettore.getNickname(), lettore.getScuderiaPref(), lettore.getModeratore(), dataFineSospensione);
        segnalazioneDAO.doUpdate(segnalazione);
        discussioneService.rimuoviCommento(segnalazione.getCommento().getIdCommento(), segnalazione.getCommento().
                getDiscussione().getIdDiscussione());
        /*dovrebbe anche cancellare il commento mo che ci penso ed a cascata tutte le segnalazioni collegate
        CommentoDAO cd = new CommentoDAO();
        cd.doDelete(segnalazione.getCommento().getIdCommento());
        */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminaSegnalazione(int idSegnalazione) {
        segnalazioneDAO.doDelete(idSegnalazione);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Segnalazione> ottieniSegnalazioni() {
        return segnalazioneDAO.doRetrieveAll();
    }

    public Segnalazione ottieniSegnalazioneDaId(int idSegnalazione){
        return segnalazioneDAO.doRetrieveById(idSegnalazione);
    }
}
