package unisa.it.formulaonline.registrazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

public class RegistrazioneServiceImpl implements RegistrazioneService{

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore registraLettore(String email, String password, String nickname, String scuderiaPreferita) {
        LettoreDAO ld = new LettoreDAO();
        //se l'email e il nickname non esistono
        Lettore lettore = null;
        if (!ld.checkExists(email, nickname))
            lettore = ld.doSave(email, password, nickname, scuderiaPreferita);
        return lettore;
    }
}
