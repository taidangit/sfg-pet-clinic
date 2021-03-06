package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private VetService vetService;

    @Autowired
    private PetTypeService petTypeService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private VisitService visitService;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");

        Specialty savedRadiology = specialityService.save(radiology);
        Specialty savedSurgery = specialityService.save(surgery);
        Specialty savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");

        Pet mikesPet = new Pet();
        mikesPet.setName("Rosco");
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setBirthDate(LocalDate.now());

        owner1.addPet(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        Pet finoasCat = new Pet();
        finoasCat.setName("just Cat");
        finoasCat.setPetType(savedCatPetType);
        finoasCat.setBirthDate(LocalDate.now());

        owner2.addPet(finoasCat);

        ownerService.save(owner2);

        Visit visitCat=new Visit();
        visitCat.setDate(LocalDate.now());
        visitCat.setDescription("Snezzy Kitty");

        finoasCat.addVisit(visitCat);

        visitService.save(visitCat);


        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.addSpecialty(savedRadiology);
        vet1.addSpecialty(savedDentistry);
        vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.addSpecialty(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
