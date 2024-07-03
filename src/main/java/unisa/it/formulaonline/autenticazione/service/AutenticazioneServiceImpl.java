package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.Date;

public class AutenticazioneServiceImpl implements AutenticazioneService{
    LettoreDAO lettoreDAO = new LettoreDAO();

    /**
     * Metodo che permette il login di un utente se i dati sono corretti e se non è sospeso.
     * @param email
     * @param password
     * @return Lettore se le condizioni precendenti sono state rispettate, {@code null} altrimenti
     */
    @Override
    public Lettore login(String email, String password) {
        Lettore l = lettoreDAO.doRetrieveByEmailPassword(email, password);
        if (l!=null && l.getDataFineSospensione().before((new Date())))
            return l;
        return null;
    }
}
