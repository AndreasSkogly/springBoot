package controller;

import model.Deltager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HelloController {
    private List<Deltager> deltagere = new ArrayList<>();

    public HelloController() {
        deltagere.add(new Deltager("23456789", "passord1", "Anne", "Panne", "Kvinne"));
        deltagere.add(new Deltager("90123456", "passord2", "Arne", "Arnesen", "Mann"));
        deltagere.add(new Deltager("12345679", "passord3", "Lars-Petter", "Helland", "Mann"));
        deltagere.add(new Deltager("34534534", "passord4", "Per", "Viskelær", "Mann"));
        deltagere.add(new Deltager("94235112", "passord5", "A-aron", "Tim-Othy", "Mann"));
        deltagere.add(new Deltager("12321378", "passord6", "Xx-x", "Xxx", "Kvinne"));
    }



    @GetMapping("/")
    public String index() {
        return "paamelding_med_melding"; // viser JSP
    }

    @PostMapping("/paameld")
    public String paameld(@ModelAttribute Deltager deltager, Model model) {
        deltagere.add(deltager);
        model.addAttribute("deltager", deltager);
        return "paameldt";
    }

    /*@PostMapping("/paameld")
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

    }*/

    @GetMapping("/deltagerliste")
    public String visDeltagerliste(Model model) {
        List<Deltager> sortert = deltagere.stream()
                .sorted(Comparator.comparing(Deltager::getFornavn)
                        .thenComparing(Deltager::getEtternavn))
                .collect(Collectors.toList());

        model.addAttribute("deltagere", sortert);
        return "deltagerliste";

    }
}
