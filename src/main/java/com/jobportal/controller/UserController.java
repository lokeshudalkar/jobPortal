package com.jobportal.controller;

import com.jobportal.Utils.JwtUtil;
import com.jobportal.dto.AuthRequest;
import com.jobportal.dto.UserRequest;
import com.jobportal.entity.JobPost;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobPostService;
import com.jobportal.service.UserDetailServiceImpl;
import com.jobportal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobPostService jobPostService;


    //To get ALL the Jobs By their title
    @GetMapping("/title/{title}")
    public List<JobPost> searchByTitle(@PathVariable String title){
        try {
            return  jobPostService.searchByTitle(title);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //To get ALL the Jobs
    @GetMapping
    public List<JobPost> getAllJobs(){
        return jobPostService.getAllJobs();
    }

    //For Deleting user
    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        if (user != null && !user.isEmpty()){
            userRepository.deleteByEmail(user);
            log.info("User Deleted SuccessFully");
            return ResponseEntity.status(HttpStatus.OK).body("User Deleted SuccessFully");
        }
        log.error(" User Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not found!");
    }
}
