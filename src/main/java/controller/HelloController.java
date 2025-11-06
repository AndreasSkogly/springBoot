package controller;

import model.Deltager;
import model.Passord;
import model.PassordService;
import model.DeltagerValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import util.LoginUtil;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HelloController {

    private final LoginUtil loginUtil;
    private final PassordService passordService;
    private final DeltagerValidator validator = new DeltagerValidator();
    private final List<Deltager> deltagere = new ArrayList<>();

    public HelloController(LoginUtil loginUtil, PassordService passordService) {
        this.loginUtil = loginUtil;
        this.passordService = passordService;

        // Dummy-data med tomme passordobjekter
        deltagere.add(new Deltager("23456789", new Passord(), "Anne", "Panne", "Kvinne"));
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("deltager", new Deltager("", new Passord(), "", "", ""));
        return "paamelding_med_melding";
    }

    @PostMapping("/paameld")
    public String paameld(
            @ModelAttribute Deltager deltager,
            @RequestParam("passord") String passord,
            @RequestParam("password_rep") String passordRep,
            Model model,
            HttpSession session) {

        // Validering
        if (!validator.erGyldig(deltager)) {
            model.addAttribute("feilmelding", "Ugyldige data!");
            model.addAttribute("deltager", deltager);
            return "paamelding_med_melding";
        }

        if (!passord.equals(passordRep)) {
            model.addAttribute("feilmelding", "Passordene samsvarer ikke!");
            model.addAttribute("deltager", deltager);
            return "paamelding_med_melding";
        }

        // Hash passord
        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(passord, salt);

        Passord p = new Passord();
        p.setSalt(salt);
        p.setHash(hash);
        deltager.setPassord(p);

        // Legg til liste
        deltagere.add(deltager);

        // Logg inn
        session.setAttribute("user_tlf", deltager.getMobil());
        session.setAttribute("user_navn", deltager.getFornavn() + " " + deltager.getEtternavn());

        return "paameldt";
    }

    @GetMapping("/deltagerliste")
    public String visListe(Model model, HttpSession session) {
        if (!loginUtil.erBrukerInnlogget(session)) {
            return "redirect:/login";
        }

        List<Deltager> sortert =
                deltagere.stream()
                        .sorted(Comparator.comparing(Deltager::getFornavn)
                                .thenComparing(Deltager::getEtternavn))
                        .collect(Collectors.toList());

        model.addAttribute("deltagere", sortert);
        return "deltagerliste";
    }
}
