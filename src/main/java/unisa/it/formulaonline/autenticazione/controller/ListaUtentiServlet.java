package unisa.it.formulaonline.autenticazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.autenticazione.service.LettoreService;
import unisa.it.formulaonline.autenticazione.service.LettoreServiceImpl;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;
import java.util.List;

/**
 * Servlet per viusualizzare la lista dei lettori non moderatori registrati
 */
@WebServlet("/listaUtenti")
public class ListaUtentiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LettoreService lettoreService = new LettoreServiceImpl();
        List<Lettore> lettoreListNonModeratori = lettoreService.ottieniLettoriNonModeratori() ;
        req.setAttribute("lettoriNonModeratori", lettoreListNonModeratori);
        req.getRequestDispatcher("/WEB-INF/moderazione/listaLettori.jsp").forward(req, resp);
    }
}
