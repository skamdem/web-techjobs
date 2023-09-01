package dev.skamdem.techjobs.controllers;

import dev.skamdem.techjobs.models.Employer;
import dev.skamdem.techjobs.models.Job;
import dev.skamdem.techjobs.models.Skill;
import dev.skamdem.techjobs.models.data.EmployerRepository;
import dev.skamdem.techjobs.models.data.JobRepository;
import dev.skamdem.techjobs.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {
        List<Job> list = IterableToCollection.getListFromIterable(jobRepository.findAll());

        //print into alphabetical order
        Collections.sort(list, new Comparator<Job>(){
            public int compare(Job s1, Job s2){
                return s1.getName().compareTo(s2.getName());
            }
        });
        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", list);
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        List<Skill> list_skills = IterableToCollection.getListFromIterable(skillRepository.findAll());

        //print into alphabetical order
        Collections.sort(list_skills, new Comparator<Skill>(){
            public int compare(Skill s1, Skill s2){
                return s1.getName().compareTo(s2.getName());
            }
        });

        List<Employer> list_employers = IterableToCollection.getListFromIterable(employerRepository.findAll());

        //print into alphabetical order
        Collections.sort(list_employers, new Comparator<Employer>(){
            public int compare(Employer s1, Employer s2){
                return s1.getName().compareTo(s2.getName());
            }
        });
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", list_employers);
        model.addAttribute("skills", list_skills);
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors,
                                    Model model,
                                    //@RequestParam int employerId,
                                    //@RequestParam(required = false) int[] skills
                                    @RequestParam(required = false) List<Integer> skills
    ) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        if (skills != null) {
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);
        }
        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> result = jobRepository.findById(jobId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Job ID: " + jobId);
            return "redirect:../";
        } else { // there is a job for that jobId!
            Job job = result.get();
            model.addAttribute("job", job);
            return "view";
        }
    }
}
