package unisa.it.formulaonline.registrazione.service;

import unisa.it.formulaonline.model.entity.Lettore;

/**
 * interfaccia per la classe per la registrazione di un nuovo lettore
 */
public interface RegistrazioneService {
    /**
     * si occupa di registrare e salvare un nuovo lettore
     * @param email da associare al lettore, non deve essere già utilizzata e deve essere in un formato valido
     * @param password tra 8 a 32 caratteri
     * @param nickname tra 5 a 30 caratteri
     * @param scuderiaPreferita del lettore, massimo 30 caratteri
     * @return il lettore con i campi registrati
     */
    Lettore registraLettore(String email, String password, String nickname, String scuderiaPreferita);
}
