package unisa.it.formulaonline.gestioneSegnalazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneService;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneServiceImpl;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.model.entity.Segnalazione;

import java.io.IOException;

/**
 * Servlet che si occupa della creazione della funzione
 */
@WebServlet("/segnalaCommento")
public class CreaSegnalazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * Riceve l'id del commento, prende l'utente dalla sessione ed il messaggio della segnalazione
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        String idCommentoStr = req.getParameter("idCommento");
        String corpo = req.getParameter("motivazione");
        String indirizzo = "WEB-INF/error_page.jsp";
        if(corpo.length()>5 && corpo.length()<=250 && idCommentoStr!=null && l!=null){
            int idCommento = Integer.parseInt(idCommentoStr);
            GestioneSegnalazioneService gs = new GestioneSegnalazioneServiceImpl();
            Segnalazione s = gs.creaSegnalazione(idCommento, l.getIdLettore(), corpo);
            if(s!=null){
                indirizzo = getServletContext().getContextPath()+"/discussione?idDiscussione=" +
                        req.getParameter("idDiscussione");
            }
        }
        resp.sendRedirect(indirizzo);
    }
}
