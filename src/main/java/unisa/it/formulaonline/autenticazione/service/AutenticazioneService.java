package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.entity.Lettore;

public interface AutenticazioneService {
   /**
    * Metodo che permette il login di un utente se i dati sono corretti e se non è sospeso.
    * Resituisce il lettore se le condizioni precendenti sono state rispettate, null altrimenti
    * @param email
    * @param password
    * @return Lettore
    */
    public Lettore login(String email, String password);
}
