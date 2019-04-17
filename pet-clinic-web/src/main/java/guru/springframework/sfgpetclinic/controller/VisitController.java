package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetService;
import guru.springframework.sfgpetclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {


    private OwnerService ownerService;
    private VisitService visitService;
    private PetService petService;

    @Autowired
    public VisitController(OwnerService ownerService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @GetMapping("/visits/new")
    public String initNewVisitForm(@PathVariable("ownerId") Long ownerId,
                                   @PathVariable("petId") Long petId,
                                   Model model) {

        Owner owner = ownerService.findById(ownerId);

        model.addAttribute("owner", owner);

        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);

        Visit visit = new Visit();

        visit.setPet(pet);

        model.addAttribute("visit", visit);

        return "visit/visitForm";
    }

    @PostMapping("/visits/save")
    public String processNewVisitForm(@PathVariable Long ownerId,
                                      @PathVariable Long petId,
                                      @Valid @ModelAttribute Visit visit,
                                      BindingResult result) {

        Pet pet = petService.findById(petId);

        if (result.hasErrors()) {
            return "visit/visitForm";
        }

        Visit savedVisit = visitService.save(visit);
        savedVisit.setPet(pet);

        pet.getVisits().add(savedVisit);

        petService.save(pet);

        return "redirect:/owners/" + ownerId + "/pets/" + pet.getId();

    }

    @GetMapping("/visits/{visitId}")
    public String showPet(@PathVariable("ownerId") Long ownerId,
                          @PathVariable("petId") Long petId,
                          @PathVariable("visitId") Long visitId, Model model) {
        model.addAttribute("ownerId", ownerId);
        model.addAttribute("petId", petId);
        model.addAttribute("visit", visitService.findById(visitId));

        return "visit/visitDetail";
    }

    @GetMapping("/visits/{visitId}/edit")
    public String initUpdateForm(@PathVariable Long ownerId,
                                 @PathVariable Long petId,
                                 @PathVariable Long visitId,
                                 Model model) {
        Owner owner = ownerService.findById(ownerId);

        Pet pet = petService.findById(petId);

        Visit visitFound=visitService.findById(visitId);

        visitFound.setPet(pet);

        model.addAttribute("owner", owner);
        model.addAttribute("pet", pet);
        model.addAttribute("visit", visitFound);

        return "visit/visitForm";
    }
}
