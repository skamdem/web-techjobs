package dev.skamdem.techjobs.models.data;

import dev.skamdem.techjobs.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kamdem
 */
@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
}
