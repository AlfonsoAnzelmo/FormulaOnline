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
 * Servlet per l'eliminazione di un commento da una discussione
 */
@WebServlet("/eliminaCommento")
public class EliminaCommentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore lettore = (Lettore) req.getSession().getAttribute("lettore");
        String idCommentoStr = req.getParameter("idCommento");
        String destinazione = "index.jsp";
        if(lettore!=null && idCommentoStr!=null){
            GestioneDiscussioneService ds= new GestioneDiscussioneImplementazione();
            int idCommento = Integer.parseInt(idCommentoStr);
            Commento commento = ds.ottieniCommentoDaId(idCommento);
            if(Objects.equals(lettore.getIdLettore(), commento.getAutore().getIdLettore())){
                ds.rimuoviCommento(commento.getIdCommento(), commento.getDiscussione().getIdDiscussione());
            }
            destinazione = getServletContext().getContextPath()+"/discussione?idDiscussione="+commento.getDiscussione().getIdDiscussione();
        }
        resp.sendRedirect(destinazione);
    }
}
