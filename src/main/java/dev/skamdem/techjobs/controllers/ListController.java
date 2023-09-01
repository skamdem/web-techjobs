package dev.skamdem.techjobs.controllers;

import dev.skamdem.techjobs.models.Skill;
import dev.skamdem.techjobs.models.data.SkillRepository;
import dev.skamdem.techjobs.models.Employer;
import dev.skamdem.techjobs.models.Job;
import dev.skamdem.techjobs.models.JobData;
import dev.skamdem.techjobs.models.data.EmployerRepository;
import dev.skamdem.techjobs.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EmployerRepository employerRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {
        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("skills", "Skills");
    }

    @RequestMapping("")
    public String list(Model model) {
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
        model.addAttribute("employers", list_employers);
        model.addAttribute("skills", list_skills);
        return "list";
    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Job> jobs;
        if (column.toLowerCase().equals("all")){
            jobs = jobRepository.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(column, value, jobRepository.findAll());
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("jobs", jobs);
        return "list-jobs";
    }
}
