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

@WebServlet("/aggiornaLettore")
public class AggiornaLettoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LettoreService lettoreService = new LettoreServiceImpl();
        Lettore lettore = (Lettore)req.getSession().getAttribute("lettore") ;
        String email = req.getParameter("email");
        String passowrd = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String scuderiaPreferita = req.getParameter("scuderiaPreferita");

        if(email == null)
            email = lettore.getEmail() ;

        if(passowrd == null)
            passowrd = lettore.getPassword() ;

        if(nickname == null)
            nickname = lettore.getNickname() ;

        if(scuderiaPreferita == null)
            scuderiaPreferita = lettore.getScuderiaPref() ;

        Lettore lettore1 = lettoreService.aggiornaLettore(lettore.getIdLettore(), email, passowrd, nickname, scuderiaPreferita) ;


        req.getSession().setAttribute("lettore",lettore1);

        req.getRequestDispatcher("aggiornaLettore.jsp").forward(req, resp);

    }
}
