package controller;

import model.Deltager;
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
    private final List<Deltager> deltagere = new ArrayList<>();

    public HelloController(LoginUtil loginUtil) {
        this.loginUtil = loginUtil;

        // Eksempel-data
        deltagere.add(new Deltager("23456789", "passord1", "Anne", "Panne", "Kvinne"));
        deltagere.add(new Deltager("90123456", "passord2", "Arne", "Arnesen", "Mann"));
        deltagere.add(new Deltager("12345679", "passord3", "Lars-Petter", "Helland", "Mann"));
        deltagere.add(new Deltager("34534534", "passord4", "Per", "Viskelær", "Mann"));
        deltagere.add(new Deltager("94235112", "passord5", "A-aron", "Tim-Othy", "Mann"));
        deltagere.add(new Deltager("12321378", "passord6", "Xx-x", "Xxx", "Kvinne"));
    }

    // Forside – viser påmeldingsskjema
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("deltager", new Deltager("", "", "", "", ""));
        return "paamelding_med_melding";
    }

    // Håndterer innsending av påmeldingsskjema
    @PostMapping("/paameld")
    public String paameld(@ModelAttribute Deltager deltager,
                          @RequestParam String password_rep,
                          Model model,
                          HttpSession session) {

        // Tøm mobilnummer
        String mobil = deltager.getMobil() != null ? deltager.getMobil().replaceAll("\\D", "") : "";

        if (!mobil.matches("\\d{8}")) {
            model.addAttribute("feilmelding", "Mobilnummer må være nøyaktig 8 siffer.");
            model.addAttribute("deltager", deltager);
            return "paamelding_med_melding";
        }

        deltager.setMobil(mobil);

        // Sjekk om mobilnummer allerede finnes
        boolean finnes = deltagere.stream()
                .anyMatch(d -> d.getMobil().equals(deltager.getMobil()));

        if (finnes) {
            model.addAttribute("feilmelding", "Dette mobilnummeret er allerede registrert.");
            model.addAttribute("deltager", deltager);
            return "paamelding_med_melding";
        }

        // Sjekk passord
        if (deltager.getPassord() == null || !deltager.getPassord().equals(password_rep)) {
            model.addAttribute("feilmelding", "Passordene samsvarer ikke!");
            model.addAttribute("deltager", deltager);
            return "paamelding_med_melding";
        }

        // Alt OK – legg til deltager
        deltagere.add(deltager);
        model.addAttribute("deltager", deltager);

        //  Sett innlogget bruker i session
        session.setAttribute("user_tlf", deltager.getMobil());
        session.setAttribute("user_navn", deltager.getFornavn() + " " + deltager.getEtternavn());

        return "paameldt";
    }

    //  Viser deltagerliste (kun innloggede)
    @GetMapping("/deltagerliste")
    public String visDeltagerliste(Model model, HttpSession session) {
        // Hvis ikke innlogget, redirect til login
        if (!loginUtil.erBrukerInnlogget(session)) {
            return "redirect:/login";
        }

        // Hent mobil og navn fra session
        String mobil = (String) session.getAttribute("user_tlf");
        String navn  = (String) session.getAttribute("user_navn");

        // Oppdater navn hvis mangler
        if ((navn == null || navn.isBlank()) && mobil != null) {
            deltagere.stream()
                    .filter(d -> mobil.equals(d.getMobil()))
                    .findFirst()
                    .ifPresent(d -> session.setAttribute("user_navn", d.getFornavn() + " " + d.getEtternavn()));
        }

        // Sorter deltagerlisten
        List<Deltager> sortert = deltagere.stream()
                .sorted(Comparator.comparing(Deltager::getFornavn)
                        .thenComparing(Deltager::getEtternavn))
                .collect(Collectors.toList());

        model.addAttribute("deltagere", sortert);
        return "deltagerliste";
    }
}
