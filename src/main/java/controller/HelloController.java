package controller;

import jakarta.servlet.http.HttpServletRequest;
import model.Deltager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.InputValidator;
import util.LoginUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HelloController {

  //  @Autowired
 //   private PassordService passordService;


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
    public String index(Model model) {
        List<String> mobilnumre = deltagere.stream()
                .map(Deltager::getMobil)
                .toList();

        model.addAttribute("mobilnumre", mobilnumre);
        return "paamelding_med_melding";
    }


    @PostMapping("/paameld")
    public String paameld(@ModelAttribute Deltager deltager,
                          @RequestParam String password_rep,
                          Model model) {
        String mobil = deltager.getMobil() != null ? deltager.getMobil().replaceAll("\\D", "") : ""; //lignende fra stack https://stackoverflow.com/questions/33053815/what-is-d-how-replaceall-d-is-working

        if (!mobil.matches("\\d{8}")) {
            model.addAttribute("feilmelding", "Mobilnummer må være nøyaktig 8 siffer.");
            model.addAttribute("deltager", deltager);
            return "redirect:paamelding_med_melding";
        }

        deltager.setMobil(mobil);

        boolean finnes = deltagere.stream()
                .anyMatch(d -> d.getMobil().equals(deltager.getMobil()));

        if (finnes){
            model.addAttribute("feilmelding", "Dette mobilnummeret er allerede registrert.");
            model.addAttribute("deltager", deltager);
            return "paamelding_med_melding";
        }


            if (deltager.getPassord() == null || !deltager.getPassord().equals(password_rep)) {
                model.addAttribute("feilmelding", "Passordene samsvarer ikke!");
                model.addAttribute("deltager", deltager);
                return "paamelding_med_melding";
            }
            
        deltagere.add(deltager);
        model.addAttribute("deltager", deltager);
        return "paameldt";
    }


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
