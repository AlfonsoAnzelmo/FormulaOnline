package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.entity.Lettore;

import java.util.Date;
import java.util.List;

public interface LettoreService {
    /**
     * Preso un lettore già esistente lo elegge a moderatore
     * @param eletto
     * @return il lettore eletto, il relativo parametro "moderatore" sarà vero se è l'elezione è avvenuta con successo
     * falso altrimenti
     */
    public Lettore nominaModeratore(int idLettore);

    /**
     * Modifica ed aggiorna i dati di un lettore già esistente
     * @param idLettore relativo al lettore da modificare
     * @param lettore
     * @return il lettore aggiornato oppure null se la modifica non è andata a buon fine
     */
    public Lettore aggiornaLettore(int idLettore, String email, String password, String nickname,
                                   String scuderiaPreferita, Boolean moderatore, Date dataFineSospensione);

    public Lettore aggiornaLettore(int idLettore, String email, String password, String nickname,String scuderiaPreferita);

    /**
     * Elimina un lettore partendo dal suo codice
      * @param idLettore
     */
    public void eliminaLettore(int idLettore);


    /**
     * ottiene un lettore partendo dall'id
     * @param idLettore
     */
    public Lettore ottieniLettoreDaId(int idLettore);
    public List<Lettore> ottieniLettoriNonModeratori();
}
