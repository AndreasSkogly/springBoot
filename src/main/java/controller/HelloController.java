package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "paamelding_med_melding"; // viser JSP
    }

    @PostMapping("/paameld")
    public String paameld(
            @RequestParam String fornavn,
            @RequestParam String etternavn,
            @RequestParam String mobil,
            @RequestParam String kjonn,
            Model model
    ) {
        model.addAttribute("fornavn", fornavn);
        model.addAttribute("etternavn", etternavn);
        model.addAttribute("mobil", mobil);
        model.addAttribute("kjonn", kjonn);
        return "paameldt"; // -> /WEB-INF/paameldt.jsp
    }
}
