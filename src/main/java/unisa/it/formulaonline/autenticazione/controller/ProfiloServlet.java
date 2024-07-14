package unisa.it.formulaonline.autenticazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.autenticazione.service.LettoreService;
import unisa.it.formulaonline.autenticazione.service.LettoreServiceImpl;
import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;
/**
 * Servlet per visualizzare il profilo di un utente
 * NON IMPLEMENTATO
 */
@WebServlet("/lettore")
public class ProfiloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idLettore");
        LettoreService ls = new LettoreServiceImpl();
        Lettore l = null;
        String address="/WEB-INF/errorPage.jsp";
        if(idStr != null) {
            int id = Integer.parseInt(idStr);
            l = ls.ottieniLettoreDaId(id);
        }
        if (l != null) {
            address = "lettore.jsp";
        }
        RequestDispatcher rd;
        rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }
}
