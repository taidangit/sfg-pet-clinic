package guru.springframework.sfgpetclinic.repository;

import guru.springframework.sfgpetclinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
