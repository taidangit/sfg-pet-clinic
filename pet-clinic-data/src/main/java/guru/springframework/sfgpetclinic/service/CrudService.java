package guru.springframework.sfgpetclinic.service;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> finadAll();

    T findById(ID id);

    void delete(T object);

    void deleteById(ID id);
}
