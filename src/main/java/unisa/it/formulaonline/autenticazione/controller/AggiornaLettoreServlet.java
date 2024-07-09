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


        if(email != null && passowrd != null && nickname != null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, passowrd, nickname, scuderiaPreferita) ;
        //caso in cui un solo campo è null
        else if(email != null && passowrd != null && nickname != null && scuderiaPreferita == null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, passowrd, nickname, lettore.getScuderiaPref()) ;
        else if(email != null && passowrd != null && nickname== null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, passowrd, lettore.getNickname(), scuderiaPreferita) ;
        else if(email != null && passowrd ==  null && nickname != null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, lettore.getPassword(), lettore.getNickname(), scuderiaPreferita) ;
        else if(email == null && passowrd != null && nickname != null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), lettore.getEmail(), lettore.getPassword(), lettore.getNickname(), scuderiaPreferita) ;

        //caso in cui due campi sono null
        else if(email != null && passowrd != null && nickname == null && scuderiaPreferita == null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, passowrd , lettore.getNickname(), lettore.getScuderiaPref()) ;
        else if(email != null && passowrd == null && nickname != null && scuderiaPreferita == null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, lettore.getPassword() , nickname, lettore.getScuderiaPref()) ;
        else if(email == null && passowrd != null && nickname != null && scuderiaPreferita == null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), lettore.getEmail(), passowrd , nickname, lettore.getScuderiaPref()) ;

        else if(email != null && passowrd == null && nickname == null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, lettore.getPassword() , lettore.getNickname(), scuderiaPreferita) ;
        else if(email == null && passowrd != null && nickname == null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), lettore.getEmail(), passowrd , lettore.getNickname(), scuderiaPreferita) ;
        else if(email != null && passowrd == null && nickname == null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, lettore.getPassword() , lettore.getNickname(), scuderiaPreferita) ;

        else if(email == null && passowrd == null && nickname != null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), lettore.getEmail(), lettore.getPassword() , nickname, scuderiaPreferita) ;

        //caso in cui tre campi sono null
        else if(email == null && passowrd == null && nickname == null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), lettore.getEmail(), lettore.getPassword() , lettore.getNickname(), scuderiaPreferita) ;
        else if(email == null && passowrd == null && nickname != null && scuderiaPreferita == null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), lettore.getEmail(), lettore.getPassword() , nickname, lettore.getScuderiaPref()) ;
        else if(email == null && passowrd != null && nickname == null && scuderiaPreferita == null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), lettore.getEmail(), passowrd , lettore.getNickname(), lettore.getScuderiaPref()) ;
        else if(email != null && passowrd == null && nickname == null && scuderiaPreferita == null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, lettore.getPassword() , lettore.getNickname(), lettore.getScuderiaPref()) ;

        else if(email != null && passowrd != null && nickname != null && scuderiaPreferita != null)
            lettoreService.aggiornaLettore(lettore.getIdLettore(), email, passowrd , nickname, scuderiaPreferita) ;


        req.getRequestDispatcher("aggiornaLettore.jsp").forward(req, resp);

    }
}
