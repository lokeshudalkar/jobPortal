package com.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_applications")
@Builder
@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;

    private String resumeUrl;
    private LocalDateTime appliedAt;

    @ManyToOne
    @JoinColumn(name = "job_post_id")
    @JsonIgnore
    private JobPost jobPost;

    @ManyToOne
    @JoinColumn(name = "seeker_id")
    @JsonIgnore
    private User seeker;
}
