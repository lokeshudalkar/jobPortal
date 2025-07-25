package com.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Map;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "job_post")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private double salary;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    @JsonIgnore
    private User recruiter;

    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL)
    private List<JobApplication> applications;

}
