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
    @PostMapping("/paameld")
    public String provAaLoggeInn(@RequestParam int user_tlf, @RequestParam String user_navn,
                                 HttpServletRequest request,	RedirectAttributes ra) {

        //Hvis ugyldig, gå til login
        if (!inputValidator.isValidUsername(String.valueOf(user_tlf))) {
            ra.addFlashAttribute("redirectMessage", "Brukernavn er ikke gyldig");
            return "redirect:loginPage";
        } else {

            //Innlogging

            loginUtil.loggInnBruker(request, user_tlf, user_navn);
            ra.addAttribute("innlogget_nr", user_tlf);
            ra.addAttribute("innlogget_navn", user_navn);

            return "redirect:deltagerliste";
        }
    }
}
