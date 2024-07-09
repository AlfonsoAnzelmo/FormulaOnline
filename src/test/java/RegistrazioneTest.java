import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.registrazione.service.RegistrazioneService;
import unisa.it.formulaonline.registrazione.service.RegistrazioneServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrazioneTest {
    private static LettoreDAO lettoreDAO;
    private static RegistrazioneService rs;
    private static Lettore l;

    @BeforeAll
    public static void init(){
        lettoreDAO = new LettoreDAO();
        rs = new RegistrazioneServiceImpl();
//        l = lettoreDAO.doSave("lettore@email.com", "123pass123", "FerrariFan", "Ferrari");
    }

    @AfterEach
    public void delete(){
        if(l!=null)
            lettoreDAO.doDelete(l.getIdLettore());
    }

    @ParameterizedTest
    @CsvSource({
            "lettore@email.com, 123pass, Fan, Ferrari",
            "mail, 123pass123, FerrariFan, Ferrari",
            "Loremipsumdolorsitametconsecteturadipiscingelitsed@email.com, 123pass123, FerrariFan, Ferrari",
            "lettore@email.com, 123pass, FerrariFan, Ferrari",
            "lettore@email.com, Loremipsumdolorsitametconsecteturadipiscingelitsed, FerrariFan, Ferrari",
            "lettore@email.com, 123pass123, nick, Ferrari",
            "lettore@email.com, 123pass123, Loremipsumdolorsitametconsecteturadipiscingelitsed, Ferrari",
            "lettore'@'email.com, 123pass123, FerrariFan, Ferrari",
            "lettore@email.com, 123%pass123, FerrariFan, Ferrari",
            "lettore@email.com, 123pass123, Ferrar, "
    })
    public void registraLettore(String email, String password, String nickname, String scuderiaPreferita){
        l = rs.registraLettore(email, password, nickname, scuderiaPreferita);
        assertNull(l);
    }
}
