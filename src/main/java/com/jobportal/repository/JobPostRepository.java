package com.jobportal.repository;

import com.jobportal.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost , Long> {

    List<JobPost> findByTitleContainingIgnoreCase(String title);
    List<JobPost> findByLocationContainingIgnoreCase(String location);
    List<JobPost> findBySalaryBetween(Double min , Double max);

    void deleteByIdAndRecruiterId(Long id , Long RecruiterId);
    Optional<JobPost> findByIdAndRecruiterId(Long id, Long recruiterId);
}
