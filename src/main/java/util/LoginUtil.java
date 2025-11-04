package util;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginUtil {

    public void loggUtBruker(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    public void loggInnBruker(HttpServletRequest request,  String mobil, String username) {

        //NB!
        loggUtBruker(request.getSession());

        HttpSession sesjon = request.getSession();
        sesjon.setAttribute("user_tlf", mobil);
        sesjon.setAttribute("user_navn", username);
       // sesjon.setAttribute("cart", new Cart());
        sesjon.setMaxInactiveInterval(20); //sekunder
    }

    public boolean erBrukerInnlogget(HttpSession session) {
        return session != null && session.getAttribute("user_tlf") != null;
    }

}

