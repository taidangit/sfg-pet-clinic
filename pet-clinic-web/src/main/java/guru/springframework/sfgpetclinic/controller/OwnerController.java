package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {


    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {

        model.addAttribute("owners", ownerService.findAll());

        return "owner/index";
    }

    @GetMapping("/find")
    public String findOwner() {

        return "notimplement";
    }

    @GetMapping("/{ownerId}")
    public String showOwner(@PathVariable("ownerId") Long ownerId, Model model) {

        model.addAttribute("owner", ownerService.findById(ownerId));

       return "owner/ownerDetail";
    }
}
