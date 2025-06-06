package com.jobportal.controller;

import com.jobportal.dto.JobRequest;
import com.jobportal.entity.JobPost;
import com.jobportal.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobPostController {
    @Autowired
    private JobPostService jobPostService;
    @PostMapping
    public JobPost createJob(@RequestBody JobRequest jobRequest){
        return jobPostService.createJob(jobRequest);
    }
}
