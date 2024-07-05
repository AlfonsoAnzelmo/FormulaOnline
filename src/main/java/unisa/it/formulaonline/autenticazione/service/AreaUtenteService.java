package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.entity.Lettore;

public interface AreaUtenteService {
    /**
     * Preso un lettore già esistente lo elegge a moderatore
     * @param eletto
     * @return il lettore eletto, il relativo parametro "moderatore" sarà vero se è l'elezione è avvenuta con successo
     * falso altrimenti
     */
    public Lettore nominaModeratore(Lettore eletto);

    /**
     * Modifica ed aggiorna i dati di un lettore già esistente
     * @param idLettore relativo al lettore da modificare
     * @param lettore
     * @return il lettore aggiornato oppure null se la modifica non è andata a buon fine
     */
    public Lettore aggiornaLettore(int idLettore, Lettore lettore);

    /**
     * Elimina un lettore partendo dal suo codice
      * @param idLettore
     */
    public void eliminaLettore(int idLettore);
}
