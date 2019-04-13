package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "specialty")
public class Specialty extends BaseEntity {

    private String description;

    @ManyToMany(mappedBy = "specialities")
    private Set<Vet> vets=new HashSet<>();

}
