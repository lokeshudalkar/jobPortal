package com.jobportal.service;

import com.jobportal.dto.JobApplicationRequest;
import com.jobportal.dto.JobRequest;
import com.jobportal.entity.JobApplication;
import com.jobportal.entity.JobPost;
import com.jobportal.entity.User;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobPostRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private UserRepository userRepository;

    public JobApplication createApplication(JobApplicationRequest jobApplicationRequest){
        User seeker = userRepository.findById(jobApplicationRequest.getSeekerId())
                .orElseThrow(() -> new RuntimeException("Job Applicant Not found"));

        if(!seeker.getRole().name().equals("SEEKER")){
            throw new RuntimeException("only role with seekers can apply for job");
        }

        JobPost jobPost = jobPostRepository.findById(jobApplicationRequest.getJobPostId())
                .orElseThrow(() -> new RuntimeException("Job Not Found"));

        JobApplication jobApplication = JobApplication.builder()
                .jobPost(jobPost)
                .seeker(seeker)
                .resumeUrl(jobApplicationRequest.getResumeUrl())
                .appliedAt(LocalDateTime.now())
                .build();
        return jobApplicationRepository.save(jobApplication);
    }

    public List<JobApplication> findBySeekerId(Long seekerId){
        return jobApplicationRepository.findBySeekerId(seekerId);
    }
}
