package unisa.it.formulaonline.registrazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static unisa.it.formulaonline.utility.PassHash.PasswordHasher;

public class RegistrazioneServiceImpl implements RegistrazioneService{

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore registraLettore(String email, String password, String nickname, String scuderiaPreferita) {
        LettoreDAO ld = new LettoreDAO();
        Lettore lettore = null;
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if(email!=null && password!=null && nickname!=null && scuderiaPreferita!=null){
            Matcher matcher = emailPattern.matcher(email);
            boolean emailValida = matcher.matches();
            if (!ld.checkExists(email, nickname) && emailValida &&
                    (5<=email.length() && (email.length()) <= 50)
                    && ((8 <= password.length())) && (password.length() <= 32)
                    && (5 <= nickname.length() && nickname.length() <= 30)
                    && scuderiaPreferita.length()<=30){
                password = PasswordHasher(password);
                lettore = ld.doSave(email, password, nickname, scuderiaPreferita);
            }
        }
        return lettore;
    }

}
