package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private PetService petService;
    private OwnerService ownerService;
    private PetTypeService petTypeService;

    @Autowired
    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }


    @ModelAttribute("types")
    public Set<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    /*@ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }*/

    @GetMapping("/pets/new")
    public String initCreationForm(@PathVariable("ownerId") Long ownerId, Model model) {

        Owner owner = ownerService.findById(ownerId);

        model.addAttribute("owner", owner);

        Pet pet = new Pet();
        pet.setOwner(owner);

        model.addAttribute("pet", pet);

        return "pet/petForm";
    }

    @PostMapping("/pets/save")
    public String processCreationForm(@PathVariable Long ownerId,
                                      @Valid @ModelAttribute Pet pet,
                                      BindingResult result, Model model) {
        Owner owner = ownerService.findById(ownerId);

        if (result.hasErrors()) {
            return "pet/petForm";
        }

        Pet savedPet = petService.save(pet);

        savedPet.setOwner(owner);
        owner.getPets().add(savedPet);

        ownerService.save(owner);

        //model.addAttribute("owner", owner);

        return "redirect:/owners/" + owner.getId();

    }

    @GetMapping("/pets/{petId}")
    public String showPet(@PathVariable("ownerId") Long ownerId,
                          @PathVariable("petId") Long petId, Model model) {

        model.addAttribute("ownerId", ownerId);
        model.addAttribute("pet", petService.findById(petId));

        return "pet/petDetail";
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long ownerId,
                                 @PathVariable Long petId,
                                 Model model) {
        Owner owner = ownerService.findById(ownerId);

        Pet petFound = petService.findById(petId);

        petFound.setOwner(owner);

        model.addAttribute("owner", owner);

        model.addAttribute("pet", petFound);

        return "pet/petForm";
    }

}
