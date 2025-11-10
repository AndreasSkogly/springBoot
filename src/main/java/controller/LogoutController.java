package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import util.LoginUtil;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private LoginUtil loginUtil;

    // Vi kunne egentlig hatt denne i LoginController, men valgte å bare ha den her for oversikt.
    @PostMapping
    public String loggUt(HttpSession session, RedirectAttributes ra) {

        loginUtil.loggUtBruker(session);

        ra.addFlashAttribute("redirectMessage", "Du er nå logget ut");
        return "redirect:/loginPage";
    }
}
