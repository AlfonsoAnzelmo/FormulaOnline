package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.Date;

public class AutenticazioneServiceImpl implements AutenticazioneService{

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore login(String email, String password) {
        LettoreDAO lettoreDAO = new LettoreDAO();

        Lettore l = lettoreDAO.doRetrieveByEmailPassword(email, password);
        /*Controlla se esiste*/
        if (l!=null){
            /*Se esiste controlla che non abbia mai ricevuto sospensioni*/
            if(l.getDataFineSospensione()!=null){
                /* Se ha ricevuto sospensioni controlla che siano scadute*/
                if(l.getDataFineSospensione().before((new Date()))){
                    return l;
                }
            }
            /*Se non ha mai ricevuto sospensioni*/
            else {
               return l;
            }
        }
        /* Se non esiste*/
        return null;
    }
}
