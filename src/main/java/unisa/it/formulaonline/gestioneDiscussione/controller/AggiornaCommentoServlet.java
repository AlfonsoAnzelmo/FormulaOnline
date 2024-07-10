package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;

import java.io.IOException;

@WebServlet("/aggiornaCommento")
public class AggiornaCommentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idCommento");
        String corpo = req.getParameter("corpo");
        String idDisStr = req.getParameter("idDiscussione");
        String destinazione = "index.jsp";
        if(idStr!=null && corpo!=null && idDisStr!=null){
            GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
            int idCommento = Integer.parseInt(idStr);
            ds.modificaCommento(corpo, idCommento);
            destinazione = getServletContext().getContextPath()+"/discussione?idDiscussione=" +idDisStr;
        }
        resp.sendRedirect(destinazione);
    }
}
