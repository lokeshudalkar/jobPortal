package com.jobportal.controller;

import com.jobportal.dto.JobApplicationRequest;
import com.jobportal.entity.JobApplication;
import com.jobportal.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-applications")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping
    public JobApplication applyToJob(@RequestBody JobApplicationRequest jobApplicationRequest){
        return jobApplicationService.createApplication(jobApplicationRequest);
    }

    @GetMapping("/seeker/{seekerId}")
    public List<JobApplication> findBySeekerId(@PathVariable Long seekerId){
        return jobApplicationService.findBySeekerId(seekerId);
    }
}
