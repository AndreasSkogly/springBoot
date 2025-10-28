package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import util.InputValidator;
import util.LoginUtil;

@Controller
@RequestMapping("/loginPage")
public class LoginController {

    @Autowired
    private InputValidator inputValidator;

    @Autowired
    private LoginUtil loginUtil;

    /*
     * GET /login er forespørselen for å hente login-skjema.
     */
    @GetMapping
    public String hentLoginSkjema() {
        return "loginPage";
    }

    /*
     * POST /login er forespørselen for å logge inn.
     */
    @PostMapping
    public String provAaLoggeInn(@RequestParam String username,
                                 HttpServletRequest request,	RedirectAttributes ra) {

        //Hvis ugyldig, gå til login
        if (!inputValidator.isValidUsername(username)) {
            ra.addFlashAttribute("redirectMessage", "Brukernavn er ikke gyldig");
            return "redirect:loginPage";
        }

        //Innlogging
        loginUtil.loggInnBruker(request, username);

        return "redirect:webshop";
    }
}
