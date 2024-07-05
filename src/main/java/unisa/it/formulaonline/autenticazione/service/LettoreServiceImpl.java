package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

public class LettoreServiceImpl implements LettoreService {
    LettoreDAO lettoreDAO = new LettoreDAO();


    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore nominaModeratore(Lettore eletto) {
        eletto.setModeratore(Boolean.TRUE);
        lettoreDAO.doUpdate(eletto, eletto.getIdLettore());
        return eletto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore aggiornaLettore(int idLettore, Lettore lettore) {
        lettoreDAO.doUpdate(lettore, idLettore);
        lettore.setIdLettore(idLettore);
        return lettore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminaLettore(int idLettore) {
        lettoreDAO.doDelete(idLettore);
    }

    @Override
    public Lettore ottieniLettoreDaId(int idLettore) {
        return lettoreDAO.doRetrieveById(idLettore);
    }
}
