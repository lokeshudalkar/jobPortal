package com.jobportal.repository;

import com.jobportal.entity.JobApplication;
import com.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long > {
    List<JobApplication> findBySeekerId(Long seekerId);
    List<JobApplication> findBySeeker(User seeker);
    List<JobApplication> findByJobPostId(Long jobId);
}
