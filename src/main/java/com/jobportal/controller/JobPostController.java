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
}
