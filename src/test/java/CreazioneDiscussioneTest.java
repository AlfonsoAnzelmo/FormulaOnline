import com.oracle.wls.shaded.org.apache.xml.utils.res.XResources_cy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.dao.DiscussioneDAO;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Discussione;
import unisa.it.formulaonline.model.entity.Lettore;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreazioneDiscussioneTest {

    private static LettoreDAO lettoreDAO;
    private static DiscussioneDAO discussioneDAO;
    private static GestioneDiscussioneService gestioneDiscussioneService;
    private static Discussione discussione;

    @BeforeAll
    public static void init(){

        //un lettore che ha effettuato il login
        lettoreDAO = new LettoreDAO();
        Lettore lettore = lettoreDAO.doRetrieveById(1);

        //istanzianzione variabili necessarie
        discussioneDAO = new DiscussioneDAO();
        gestioneDiscussioneService = new GestioneDiscussioneImplementazione();
    }

    @AfterEach
    public void delete(){
        //se viene erroneamente viene creata comunque una discussione, dopo ogni test viene eliminata
        //ripristinando lo stato del DB
        if(discussione!=null)
            discussioneDAO.doDelete(discussione.getIdDiscussione());
    }

    @ParameterizedTest
    @CsvSource({
            "Nuovo titolo,1,1,''",
            "Nuovo titolo, 1, 1,Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries but also the leap into electronic typesetting remaining essentially unchanged. it was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages and more recently with desktop publishing",
            "'',1,1,commento",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.,1,1,commento",
            "Nuovo titolo,0,1,commento"
    })
    public void creaDiscussioneFailTest(String titolo, int idCategoria, int idAutore, String commento){
        discussione = gestioneDiscussioneService.creaDiscussione(titolo, idCategoria, idAutore, commento);
        assertNull(discussione);
    }

    @ParameterizedTest
    @CsvSource({
            "Nuovo titolo, 1, 1, commento"})
    public void creaDiscussioneSuccessTest(String titolo, int idCategoria, int idAutore, String commento){
        discussione = gestioneDiscussioneService.creaDiscussione(titolo, idCategoria, idAutore, commento);
        assertNotNull(discussione);
    }

}
