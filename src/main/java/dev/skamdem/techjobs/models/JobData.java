package dev.skamdem.techjobs.models;

import java.util.ArrayList;
import java.util.List;

public class JobData {

    /**
     * Returns the results of searching the Jobs data by field and search term.
     * <p>
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column  Job field that should be searched.
     * @param value   Value of the field to search for.
     * @param allJobs The list of jobs to search.
     * @return List of all jobs matching the criteria.
     */
    public static ArrayList<Job> findByColumnAndValue(String column, String value, Iterable<Job> allJobs) {
        ArrayList<Job> results = new ArrayList<>();
        if (value.toLowerCase().equals("all")) {//i.e. searchTerm = all
            return (ArrayList<Job>) allJobs;
        }
        if (column.equals("all")) {//i.e. searchType = all
            results = findByValue(value, allJobs);
            return results;
        }
        for (Job job : allJobs) {
            /*String aValue = getFieldValue(job, column);
            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            }*/
            if (getFieldValue(job, column, value)) {
                results.add(job);
            }
        }
        return results;
    }

    public static boolean getFieldValue(Job job, String searchType, String searchTerm) {
        if (searchType.equals("skills")) {
            List<Skill> skills = job.getSkills();
            for (Skill skill : skills) {
                if (skill.toString().toLowerCase().contains(searchTerm.toLowerCase()) || skill.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                    return true;
                }
            }
        } else if (searchType.equals("employer")) {
            if (job.getEmployer().toString().toLowerCase().contains(searchTerm.toLowerCase()) || job.getEmployer().getLocation().toLowerCase().contains(searchTerm.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Search all Job fields for the given term.
     *
     * @param value   The search term to look for.
     * @param allJobs The list of jobs to search.
     * @return List of all jobs with at least one field containing the value.
     */
    public static ArrayList<Job> findByValue(String value, Iterable<Job> allJobs) {
        ArrayList<Job> results = new ArrayList<>();
        for (Job job : allJobs) {
            if (job.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            } else if (job.getEmployer().toString().toLowerCase().contains(value.toLowerCase()) || job.getEmployer().getLocation().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            } else {
                List<Skill> skills = job.getSkills();
                for (Skill skill : skills) {
                    if (skill.toString().contains(value.toLowerCase()) || skill.getDescription().contains(value.toLowerCase())) {
                        results.add(job);
                        break;
                    }
                }
            }
        }
        return results;
    }
}
