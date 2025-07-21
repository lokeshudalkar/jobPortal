package com.jobportal.controller;

import com.jobportal.dto.JobRequest;
import com.jobportal.entity.JobPost;
import com.jobportal.entity.User;
import com.jobportal.repository.JobPostRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job-post")
public class JobPostController {
    @Autowired
    JobPostRepository jobPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobPostService jobPostService;


    @PostMapping
    public JobPost createJob(@RequestBody JobRequest jobRequest){
        return jobPostService.createJob(jobRequest);
    }

    @Transactional
    @PutMapping("/update-job/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id , @RequestBody JobRequest jobRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Long recruiterId = user.getId();
        boolean updated = jobPostService.updateJob(id , recruiterId , jobRequest);
        if (updated) {
            return  ResponseEntity.ok("Job updated successfully");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update this job post.");
    }

    @Transactional
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Long recruiterId = user.getId();
        jobPostRepository.deleteByIdAndRecruiterId(id , recruiterId);
        return new ResponseEntity<>("Job with ID " + id + " deleted.", HttpStatus.OK);
    }
}
