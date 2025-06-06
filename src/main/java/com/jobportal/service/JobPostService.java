package com.jobportal.service;

import com.jobportal.dto.JobRequest;
import com.jobportal.entity.JobPost;
import com.jobportal.entity.User;
import com.jobportal.repository.JobPostRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobPostService {
    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private UserRepository userRepository;

    public JobPost createJob(JobRequest jobRequest){
        User recruiter = userRepository.findById(jobRequest.getRecruiterId()).orElseThrow(() -> new RuntimeException("Recruiter not found"));


        JobPost jobPost = JobPost.builder()
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .location(jobRequest.getLocation())
                .salary(jobRequest.getSalary())
                .recruiter(recruiter)
                .time(LocalDateTime.now())
                .build();
        return jobPostRepository.save(jobPost);
    }
}
