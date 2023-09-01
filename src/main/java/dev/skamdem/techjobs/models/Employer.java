package dev.skamdem.techjobs.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "location is required")
    @Size(min = 3, message = "location must be at least 3 characters long")
    private String location;

    @OneToMany //(mappedBy = "employer")
    @JoinColumn(name = "employer_id")
    private final List<Job> jobs = new ArrayList<>();

    public Employer() {
    }

    public Employer(@NotBlank(message = "name is required")
                    @Size(min = 3, message = "name must be at least 3 characters long") String name,
                    @NotBlank(message = "location is required")
                    @Size(min = 3, message = "location must be at least 3 characters long") String location) {
        this.setName(name);
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
