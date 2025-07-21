package com.jobportal.repository;

import com.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {
//    @Query(value = "SELECT * FROM user WHERE BINARY email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);
    @Transactional
    void deleteByEmail(String email);
}
