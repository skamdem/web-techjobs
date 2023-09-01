package dev.skamdem.techjobs.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Entity
public class Job extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Employer is required")
    private Employer employer;

    @ManyToMany
    private final List<Skill> skills = new ArrayList<>();

    public Job() {
    }

    public Job(@NotBlank(message = "Job name is required")
               @Size(min = 3, message = "Job name must be at least 3 characters long") String name,
               Employer anEmployer) {
        this.setName(name);
        this.employer = anEmployer;
    }

    // Getters and setters.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> someSkills) {
        ListIterator<Skill> listIterator = someSkills.listIterator();
        while (listIterator.hasNext()) {
            Skill skill = listIterator.next();
            if (!this.skills.contains(skill)) {
                this.skills.add(skill);
            }
        }
    }
}
