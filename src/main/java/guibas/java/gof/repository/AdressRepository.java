package guibas.java.gof.repository;

import guibas.java.gof.model.Adress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends CrudRepository<Adress, String> {
}
