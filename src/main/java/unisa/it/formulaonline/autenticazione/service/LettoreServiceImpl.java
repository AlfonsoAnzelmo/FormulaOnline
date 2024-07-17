package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static unisa.it.formulaonline.utility.PassHash.PasswordHasher;

public class LettoreServiceImpl implements LettoreService {


    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore nominaModeratore(int idLettore) {
        LettoreDAO lettoreDAO = new LettoreDAO();

        Lettore lettore = lettoreDAO.doRetrieveById(idLettore);

        if(lettore!=null) {
            lettoreDAO.doUpdate(lettore.getIdLettore(), lettore.getEmail(), lettore.getPassword(),
                    lettore.getNickname(), lettore.getScuderiaPref(), Boolean.TRUE, lettore.getDataFineSospensione());
            lettore.setModeratore(Boolean.TRUE);
        }
        return lettore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore aggiornaLettore(int idLettore, String email, String password, String nickname,
                                   String scuderiaPreferita, Boolean moderatore, Date dataFineSospensione) {

        LettoreDAO ld = new LettoreDAO();
        Lettore lettore = null;
        password = PasswordHasher(password);
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        boolean emailValida = matcher.matches();
        if (!ld.checkExists(email, nickname) && emailValida &&
                (5 <= email.length() && (email.length()) <= 50)
                && ((8 <= password.length())) && (password.length() <= 32)
                && (5 <= nickname.length() && nickname.length() <= 30)
                && scuderiaPreferita.length() <= 30) {
            password = PasswordHasher(password);
            ld.doUpdate(idLettore, email, password, nickname, scuderiaPreferita, moderatore, dataFineSospensione);
            lettore = new Lettore(idLettore, email, password, nickname, scuderiaPreferita, moderatore, dataFineSospensione);
        }
        return lettore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore aggiornaLettore(int idLettore, String email, String password, String nickname, String
            scuderiaPreferita) {

        LettoreDAO lettoreDAO = new LettoreDAO();
        password = PasswordHasher(password);
        lettoreDAO.doUpdate(idLettore, email, password, nickname, scuderiaPreferita);
        Lettore lettore = new Lettore(idLettore, email, password, nickname, scuderiaPreferita);
        return lettore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminaLettore(int idLettore) {
        LettoreDAO lettoreDAO = new LettoreDAO();

        lettoreDAO.doDelete(idLettore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore ottieniLettoreDaId(int idLettore) {

        LettoreDAO lettoreDAO = new LettoreDAO();
        return lettoreDAO.doRetrieveById(idLettore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Lettore> ottieniLettoriNonModeratori() {
        LettoreDAO lettoreDAO = new LettoreDAO();
        return lettoreDAO.doRetrieveAllNonModeratore();
    }
}
