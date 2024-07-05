package unisa.it.formulaonline.registrazione.service;

import unisa.it.formulaonline.model.entity.Lettore;

public interface RegistrazioneService {
    /**
     * si occupa di registrare e salvare un nuovo lettore
     * @param email
     * @param password
     * @param nickname
     * @param scuderiaPreferita
     * @return il lettore con i campi registrati
     */
    public Lettore registraLettore(String email, String password, String nickname, String scuderiaPreferita);
}
