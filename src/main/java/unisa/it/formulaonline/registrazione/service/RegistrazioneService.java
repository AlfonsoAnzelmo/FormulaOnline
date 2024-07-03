package unisa.it.formulaonline.registrazione.service;

import unisa.it.formulaonline.model.entity.Lettore;

public interface RegistrazioneService {
    /**
     * si occupa di registrare e salvare un nuovo lettore
     * @param lettore da registrare
     * @return il lettore con i campi aggiornati
     */
    public Lettore registraLettore(Lettore lettore);
}
