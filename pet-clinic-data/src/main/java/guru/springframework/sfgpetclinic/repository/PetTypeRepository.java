package guru.springframework.sfgpetclinic.repository;

import guru.springframework.sfgpetclinic.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}
