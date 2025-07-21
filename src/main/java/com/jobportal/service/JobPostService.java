package com.jobportal.service;

import com.jobportal.dto.JobRequest;
import com.jobportal.entity.JobPost;
import com.jobportal.entity.User;
import com.jobportal.repository.JobPostRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobPostService {
    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public JobPost createJob(JobRequest jobRequest){
        User recruiter = userRepository.findById(jobRequest.getRecruiterId()).orElseThrow(() -> new RuntimeException("Recruiter not found"));

        if(!recruiter.getRole().name().equals("RECRUITER")){
            throw new RuntimeException("only role with RECRUITER can post jobs");
        }
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

    public List<JobPost> getAllJobs() {
        return jobPostRepository.findAll();
    }

    public List<JobPost> searchByTitle(String title){
        return jobPostRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<JobPost> searchByLocation(String location){
        return jobPostRepository.findByLocationContainingIgnoreCase(location);
    }
    public List<JobPost> searchBySalaryRange(Double minSalary , Double maxSalary){
        return jobPostRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    @Transactional
    public boolean updateJob(Long id , Long recruiterId , JobRequest updateJob){
        Optional<JobPost> optional = jobPostRepository.findByIdAndRecruiterId(id, recruiterId);
//        JobPost job = jobPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));

        if(optional.isPresent()){
            JobPost job = optional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setLocation(updateJob.getLocation());
            job.setSalary(updateJob.getSalary());
            job.setTime(LocalDateTime.now());
            jobPostRepository.save(job);
            return true;
        }
        return false;
    }

}
