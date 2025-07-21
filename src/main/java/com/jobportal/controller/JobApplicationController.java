package com.jobportal.controller;

import com.jobportal.dto.JobApplicationRequest;
import com.jobportal.entity.JobApplication;
import com.jobportal.entity.User;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-applications")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @PostMapping
    public ResponseEntity<?> applyToJob(@RequestBody JobApplicationRequest jobApplicationRequest){
        return new ResponseEntity<>(jobApplicationService.createApplication(jobApplicationRequest)
        ,HttpStatus.CREATED);
    }

    @GetMapping("/seeker/{seekerId}")
    public ResponseEntity<?> findBySeekerId(@PathVariable Long seekerId){
        List<JobApplication> bySeekerId = jobApplicationService.findBySeekerId(seekerId);
        if (bySeekerId != null) {
            return new ResponseEntity<>(bySeekerId , HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/jobId/{id}")
    public ResponseEntity<?> getAllAplicationOfJob(@PathVariable Long id){
        List<JobApplication> byJobId = jobApplicationService.findByJobId(id);
        if (byJobId != null) {
            return new ResponseEntity<>(byJobId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAllApllicationOfUser(@AuthenticationPrincipal User seeker){
        List<JobApplication> bySeeker = jobApplicationRepository.findBySeeker(seeker);
        if(bySeeker != null && !bySeeker.isEmpty()) {
            return new ResponseEntity<>(bySeeker, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
