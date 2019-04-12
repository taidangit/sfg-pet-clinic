package guru.springframework.sfgpetclinic.repository;

import guru.springframework.sfgpetclinic.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
