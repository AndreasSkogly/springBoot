package controller;

import jakarta.servlet.http.HttpServletRequest;
import model.Deltager;
import model.DeltagerRepository;
import model.Passord;
import model.PassordService;
import model.DeltagerValidator;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import util.LoginUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class HelloController {

    private final LoginUtil loginUtil;
    private final PassordService passordService;
    private final DeltagerRepository repository;
    private final DeltagerValidator validator = new DeltagerValidator();

    public HelloController(LoginUtil loginUtil,
                           PassordService passordService,
                           DeltagerRepository repository) {
        this.loginUtil = loginUtil;
        this.passordService = passordService;
        this.repository = repository;

    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("deltager", new Deltager("", new Passord(), "", "", ""));
        return "paamelding_med_melding";
    }

    @PostMapping("/paameld")
    public String paameld(
            @RequestParam("fornavn") String fornavn,
            @RequestParam("etternavn") String etternavn,
            @RequestParam("mobil") String mobil,
            @RequestParam("kjonn") String kjonn,
            @RequestParam("passord") String passord,
            @RequestParam("password_rep") String passordRep,
            Model model,
            HttpSession session) {

        if (!passord.equals(passordRep)) {
            model.addAttribute("feilmelding", "Passordene samsvarer ikke!");
            return "paamelding_med_melding";
        }


        if (repository.existsByMobil(mobil)) {
            model.addAttribute("feilmelding", "Mobilnummer er allerede registrert!");
            return "paamelding_med_melding";
        }


        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(passord, salt);

        Passord p = new Passord();
        p.setSalt(salt);
        p.setHash(hash);

        Deltager deltager = new Deltager(mobil, p, fornavn, etternavn, kjonn);

        repository.save(deltager);

        session.setAttribute("user_tlf", deltager.getMobil());
        session.setAttribute("user_navn", deltager.getFornavn() + " " + deltager.getEtternavn());

        model.addAttribute("deltager", deltager);

        return "paameldt";
    }


    @GetMapping("/deltagerliste")
    public String deltagerliste(Model model, HttpServletRequest request) {
        model.addAttribute("deltagere", repository.findAllByOrderByFornavnAsc());
        return "deltagerliste";
    }
}
