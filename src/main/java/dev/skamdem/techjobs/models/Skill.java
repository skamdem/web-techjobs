package dev.skamdem.techjobs.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @Size(max = 100, message = "Skill description must be at most 100 characters long")
    private String description;

    @ManyToMany(mappedBy = "skills")
    private final List<Job> jobs = new ArrayList<>();

    public Skill() {
    }

    public Skill(@NotBlank(message = "Skill name is required")
                 @Size(min = 3, message = "Skill name must be at least 3 characters long") String name,
                 @Size(max = 100, message = "Skill description must be at most 100 characters long") String description) {
        this.setName(name);
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}