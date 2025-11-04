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
<<<<<<< HEAD
    @PostMapping("/paameld")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("mobil") String mobil,
                          HttpServletRequest request,
                          RedirectAttributes ra) {

        if (!inputValidator.isValidUsername(username)) {
            ra.addFlashAttribute("redirectMessage", "Brukernavn er ikke gyldig");
            return "redirect:/loginPage";
        }

        loginUtil.loggInnBruker(request, username, mobil);
        ra.addAttribute("user_navn", username);
        ra.addAttribute("user_tlf", mobil);
        return "redirect:/deltagerliste";
=======
    @PostMapping
    public String provAaLoggeInn(@RequestParam String username, @RequestParam String mobil,
                                 HttpServletRequest request,	RedirectAttributes ra) {

        //Hvis ugyldig, gå til login
        if (!inputValidator.isValidUsername(username)) {
            ra.addFlashAttribute("redirectMessage", "Brukernavn er ikke gyldig");
            return "redirect:loginPage";
        }

        //Innlogging
        ra.addFlashAttribute("mobil", mobil );
        ra.addFlashAttribute("username", username);
        loginUtil.loggInnBruker(request, username);

        return "redirect:webshop";
>>>>>>> 98bd26a666e62c5d8f68271bfcd452b1a3afd151
    }

}
