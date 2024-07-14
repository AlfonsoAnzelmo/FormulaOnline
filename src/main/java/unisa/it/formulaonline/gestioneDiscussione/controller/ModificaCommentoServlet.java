package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;
import java.util.Objects;

/**
 * Servlet che riporta alla pagina di modifica di un commento
 * Non implementata
 */
@WebServlet("/modificaCommento")
public class ModificaCommentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCommentoStr = req.getParameter("idCommento");
        Lettore lettore = (Lettore) req.getSession().getAttribute("lettore");
        String destinazione = "index.jsp";
        if(idCommentoStr!=null && lettore!=null){
            GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
            int idCommento = Integer.parseInt(idCommentoStr);
            Commento commento = ds.ottieniCommentoDaId(idCommento);
            /* controlla che l'autore del commento sia lo stesso che abbia fatto la proposta di modifica */
            if(Objects.equals(commento.getAutore().getIdLettore(), lettore.getIdLettore())){
                req.setAttribute("commento", commento);
                destinazione = "modificacommento.jsp";
                req.getRequestDispatcher(destinazione).forward(req, resp);
            }
        }
        resp.sendRedirect(destinazione);
    }
}
