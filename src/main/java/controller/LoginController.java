package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import util.InputValidator;
import util.LoginUtil;

import model.Deltager;
import model.DeltagerRepository;
import model.Passord;
import model.PassordService;

import java.util.Optional;

@Controller
@RequestMapping("/loginPage")
public class LoginController {

    @Autowired
    private InputValidator inputValidator; // ikke brukt nå, men fint å ha for senere

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private DeltagerRepository repository;

    @Autowired
    private PassordService passordService;

    @GetMapping
    public String visLoginSide() {
        return "loginPage";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam(value="mobil", required=false) String mobil,
                          @RequestParam(value="passord", required=false) String passord,
                          HttpServletRequest request,
                          RedirectAttributes ra) {

        // Sjekk input om mobilnummer og passord er skrevet inn når en skal logge inn igjen på brukeren sin
        if (mobil == null || mobil.isBlank() || passord == null || passord.isBlank()) {
            ra.addFlashAttribute("redirectMessage", "Skriv inn både mobilnummer og passord");
            return "redirect:/loginPage";
        }

        // behold kun siffer, fjerner unødvendige mellomrom og diverse
        String ms = mobil.replaceAll("\\D", "");



        Optional<Deltager> opt = repository.findById(ms);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("redirectMessage", "Bruker finnes ikke");
            return "redirect:/loginPage";
        }

        Deltager d = opt.get();
        Passord p = d.getPassord();


        boolean riktig = passordService.erKorrektPassord(passord, p.getSalt(), p.getHash());
        if (!riktig) {
            ra.addFlashAttribute("redirectMessage", "Feil passord");
            return "redirect:/loginPage";
        }

        String username = d.getFornavn() + " " + d.getEtternavn();
        loginUtil.loggInnBruker(request, username, d.getMobil());

        request.getSession().setAttribute("user_tlf", d.getMobil());
        request.getSession().setAttribute("user_navn",
                d.getFornavn() + " " + d.getEtternavn());

        return "redirect:/deltagerliste";
    }
}
