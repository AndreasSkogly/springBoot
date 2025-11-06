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

    @PostMapping("/login")
    public String doLogin(@RequestParam("mobil") String mobil,
                          HttpServletRequest request,
                          RedirectAttributes ra) {

        if (mobil == null || mobil.trim().isEmpty()) {
            ra.addFlashAttribute("redirectMessage", "Skriv inn mobilnummer");
            return "redirect:/loginPage";
        }

        // Foreløpig logger vi inn kun på mobil (navn hentes i HelloController)
        loginUtil.loggInnBruker(request, /*username*/ "", mobil.trim());

        return "redirect:/deltagerliste";
    }






}
