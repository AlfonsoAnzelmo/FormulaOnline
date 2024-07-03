package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.entity.Lettore;

public interface AutenticazioneService {
   /**
    * Metodo che permette il login di un utente se i dati sono corretti e se non è sospeso.
    * @param email
    * @param password
    * @return Lettore se le condizioni precendenti sono state rispettate, {@code null} altrimenti
    */
    public Lettore login(String email, String password);
}
