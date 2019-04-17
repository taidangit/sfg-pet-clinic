package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/find")
    public String findOwner(Model model) {

        model.addAttribute("owner", Owner.builder().build());
        return "owner/findOwner";
    }

    @PostMapping
    public String processFindForm(@ModelAttribute Owner owner, BindingResult result, Model model) {

        // find owners by last name
        List<Owner> results =
                ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            return "owner/findOwner";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("owners", results);
            return "owner/ownersList";
        }
    }


    @GetMapping("/{ownerId}")
    public String showOwner(@PathVariable("ownerId") Long ownerId, Model model) {

        model.addAttribute("owner", ownerService.findById(ownerId));

        return "owner/ownerDetail";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/ownerForm";
    }

    @PostMapping("/save")
    public String processCreationForm(@Valid @ModelAttribute Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owner/ownerForm";
        }

        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));

        return "owner/ownerForm";
    }
}
