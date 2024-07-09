package unisa.it.formulaonline.autenticazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.autenticazione.service.LettoreService;
import unisa.it.formulaonline.autenticazione.service.LettoreServiceImpl;

import java.io.IOException;

@WebServlet("/nominaModeratore")
public class NominaModeratore extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LettoreService lettoreService = new LettoreServiceImpl();
        int idLettore = Integer.parseInt(req.getParameter("idLettore"));
        lettoreService.nominaModeratore(idLettore) ;
        req.getRequestDispatcher("listaLettori.jsp").forward(req, resp);
    }
}
