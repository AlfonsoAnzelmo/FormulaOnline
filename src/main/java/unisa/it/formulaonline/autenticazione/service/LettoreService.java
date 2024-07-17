package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.entity.Lettore;

import java.util.Date;
import java.util.List;

/**
 * Espone i metodi che permettono la gestione di un lettore nel sistema
 */
public interface LettoreService {
    /**
     * Preso un lettore già esistente lo elegge a moderatore
     * @param idLettore id del lettore da eleggere
     * @return il lettore eletto, il relativo parametro "moderatore" sarà vero se è l'elezione è avvenuta con successo
     * falso altrimenti
     */
    public Lettore nominaModeratore(int idLettore);

    /**
     * Modifica ed aggiorna i dati di un lettore già esistente
     * @param idLettore relativo al lettore da modificare
     * @param email il nuovo indirizzo email
     * @param password la nuova password
     * @param nickname il nuovo nickname
     * @param scuderiaPreferita la nuova scuderia preferita
     * @param moderatore se il lettore è un moderatore TRUE, FALSE altrimenti
     * @param dataFineSospensione la data della fine della sua sospensione, può essere null
     *                            se non ha mai ricevuto una sospensione
     * @return il lettore aggiornato oppure null se la modifica non è andata a buon fine
     */
    public Lettore aggiornaLettore(int idLettore, String email, String password, String nickname,
                                   String scuderiaPreferita, Boolean moderatore, Date dataFineSospensione);

    /**
     * Modifica ed aggiorna i dati di un lettore già esistente, con solo i campi che un lettore può modificare
     * in autonomia
     * @param idLettore id del lettore da modificare
     * @param email il nuovo indirizzo email
     * @param password la nuova password
     * @param nickname il nuovo nickname
     * @param scuderiaPreferita la nuova scuderia preferita
     * @return il lettore con i campi aggiornati
     */
    public Lettore aggiornaLettore(int idLettore, String email, String password, String nickname,String scuderiaPreferita);

    /**
     * Elimina un lettore partendo dal suo codice
     * NON IMPLEMENTATO
      * @param idLettore id del lettore da eliminare
     */
    public void eliminaLettore(int idLettore);


    /**
     * ottiene un lettore partendo dall'id
     * @param idLettore id del lettore da recuperare
     * @return il lettore richesto, null se non esiste
     */
    public Lettore ottieniLettoreDaId(int idLettore);

    /**
     * restituisce una lista dei lettori non moderatori
     * @return la lista di lettori che non sono moderatori
     */
    public List<Lettore> ottieniLettoriNonModeratori();
}
