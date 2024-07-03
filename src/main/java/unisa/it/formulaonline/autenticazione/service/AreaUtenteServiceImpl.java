package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

public class AreaUtenteServiceImpl implements AreaUtenteService{
    LettoreDAO lettoreDAO = new LettoreDAO();


    /**
     * Preso un lettore lo elegge a moderatore
     * @param eletto
     * @return il lettore eletto, il relativo parametro "moderatore" sarà vero se è l'elezione è avvenuta con successo
     * falso altrimenti
     */
    @Override
    public Lettore nominaModeratore(Lettore eletto) {
        eletto.setModeratore(Boolean.TRUE);
        lettoreDAO.doUpdate(eletto, eletto.getIdLettore());
        return eletto;
    }

    /**
     * Modifica ed aggiorna i dati di un lettore già esistente
     * @param idLettore relativo al lettore da modificare
     * @param lettore
     * @return
     */
    @Override
    public Lettore aggiornaLettore(int idLettore, Lettore lettore) {
        lettoreDAO.doUpdate(lettore, idLettore);
        lettore.setIdLettore(idLettore);
        return lettore;
    }

    /**
     * Elimina un lettore partendo dal suo codice
     * @param idLettore
     */
    @Override
    public void eliminaLettore(int idLettore) {
        lettoreDAO.doDelete(idLettore);
    }
}
