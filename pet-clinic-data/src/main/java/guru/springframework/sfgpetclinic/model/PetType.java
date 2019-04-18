package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "type")
public class PetType extends BaseEntity {

    @Builder
    public PetType(Long id, String name, Set<Pet> pets) {
        super(id);
        this.name = name;
        this.pets = pets;
    }

    @Column(name = "name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "petType")
    private Set<Pet> pets=new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
