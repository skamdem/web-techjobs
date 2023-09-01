package dev.skamdem.techjobs.models.data;

import dev.skamdem.techjobs.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kamdem
 */
@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
