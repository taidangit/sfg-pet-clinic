package guru.springframework.sfgpetclinic.model;

import java.util.Locale;

public class Pet extends BaseEntity{

    private PetType petType;
    private Owner owner;
    private Locale birthDate;

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Locale getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Locale birthDate) {
        this.birthDate = birthDate;
    }
}
