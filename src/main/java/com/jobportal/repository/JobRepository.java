package com.jobportal.repository;

import com.jobportal.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobPost , Long> {

}
