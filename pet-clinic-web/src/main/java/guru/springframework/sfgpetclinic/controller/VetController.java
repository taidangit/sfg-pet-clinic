package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VetController {

    @Autowired
    private VetService vetService;

    @GetMapping("/vets")
    public String listVets(Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vet/vetList";
    }
}
