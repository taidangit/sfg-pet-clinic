package guru.springframework.sfgpetclinic.repository;

import guru.springframework.sfgpetclinic.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
