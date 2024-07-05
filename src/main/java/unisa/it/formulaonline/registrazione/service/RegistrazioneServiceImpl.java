package unisa.it.formulaonline.registrazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

public class RegistrazioneServiceImpl implements RegistrazioneService{

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore registraLettore(Lettore lettore) {
        LettoreDAO ld = new LettoreDAO();
        //se l'email e il nickname non esistono
        if (!ld.checkExists(lettore.getEmail(), lettore.getNickname()))
            lettore = ld.doSave(lettore);
        else
            lettore = null;
        return lettore;
    }
}
