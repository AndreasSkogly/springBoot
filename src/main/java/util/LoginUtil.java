package util;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginUtil {

    public void loggInnBruker(HttpServletRequest request, String mobil, String username) {
        HttpSession old = request.getSession(false);
        if (old != null) {
            old.invalidate();
        }
        HttpSession sesjon = request.getSession(true);
        request.changeSessionId();
        sesjon.setAttribute("user_tlf", mobil);
        sesjon.setAttribute("user_navn", username == null ? "" : username);
        sesjon.setMaxInactiveInterval(20 * 60);
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

