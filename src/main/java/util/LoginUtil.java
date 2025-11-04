package util;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginUtil {

    public void loggInnBruker(HttpServletRequest request, String mobil, String username) {
        HttpSession sesjon = request.getSession(true);
        sesjon.setAttribute("user_tlf", mobil);
        sesjon.setAttribute("user_navn", username); // kan være tom string ved login via mobil
        sesjon.setMaxInactiveInterval(20 * 60); // 20 min
    }

    public boolean erBrukerInnlogget(HttpSession session) {
        return session != null && session.getAttribute("user_tlf") != null;
    }

    public void loggUtBruker(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }
}
