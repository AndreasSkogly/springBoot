package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import util.InputValidator;
import util.LoginUtil;

@Controller
@RequestMapping("/loginPage")
public class LoginController {

    @Autowired
    private InputValidator inputValidator; // ikke brukt nå, men fint å ha for senere

    @Autowired
    private LoginUtil loginUtil;

    @GetMapping
    public String visLoginSide() {
        return "loginPage";
    }

    @Autowired
    private model.DeltagerRepository repository;

    @PostMapping("/login")
    public String doLogin(@RequestParam(value="mobil", required=false) String mobil,
                          HttpServletRequest request,
                          RedirectAttributes ra) {
        if (mobil == null || mobil.isBlank()) {
            ra.addFlashAttribute("redirectMessage", "Skriv inn mobilnummer");
            return "redirect:/loginPage";
        }

        //Finner navn knyttet til mobilnummer
        String ms = mobil.trim();
        String username = repository.findById(ms)
                .map(d -> d.getFornavn() + " " + d.getEtternavn())
                .orElse(""); // evt. vis feilmelding hvis ikke finnes

        loginUtil.loggInnBruker(request, ms, username);
        return "redirect:/deltagerliste";
    }







}
