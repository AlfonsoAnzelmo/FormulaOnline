package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.Date;

public class AutenticazioneServiceImpl implements AutenticazioneService{
    LettoreDAO lettoreDAO = new LettoreDAO();

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore login(String email, String password) {
        Lettore l = lettoreDAO.doRetrieveByEmailPassword(email, password);
        if (l!=null && l.getDataFineSospensione().before((new Date())))
            return l;
        return null;
    }
}
