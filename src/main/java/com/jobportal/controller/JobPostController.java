package com.jobportal.controller;

import com.jobportal.dto.JobRequest;
import com.jobportal.entity.JobPost;
import com.jobportal.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobPostController {
    @Autowired
    private JobPostService jobPostService;
    @PostMapping
    public JobPost createJob(@RequestBody JobRequest jobRequest){
        return jobPostService.createJob(jobRequest);
    }

    @GetMapping
    public List<JobPost> getAllJobs(){
        return jobPostService.getAllJobs();
    }
    @GetMapping("/title/{title}")
    public List<JobPost> searchByTitle(@PathVariable String title){
        return  jobPostService.searchByTitle(title);
    }
    @GetMapping("/location/{location}")
    public List<JobPost> searchByLocation(@PathVariable String location){
        return  jobPostService.searchByLocation(location);
    }
    @GetMapping("/salary/{min}/{max}")
    public List<JobPost> searchBetweenSalaryRange(@PathVariable Double min ,@PathVariable Double max){
        return  jobPostService.searchBySalaryRange(min , max);
    }

    @GetMapping("/search")
    public List<JobPost> searchJob(
            @RequestParam(required = false) String title ,
            @RequestParam(required = false) String location ,
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max){
        if(title != null){
            return jobPostService.searchByTitle(title);
        }
        if(location != null){
            return jobPostService.searchByLocation(location);
        }
        if (min != null && max != null){
            return jobPostService.searchBySalaryRange(min , max);
        }
        return jobPostService.getAllJobs();
    }

    @PutMapping("/update-job/{id}")
    public JobPost updateJob(@PathVariable Long id , @RequestBody JobPost updateJob){
        return jobPostService.updateJob(id , updateJob);
    }
}
