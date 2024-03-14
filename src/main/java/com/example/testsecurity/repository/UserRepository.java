package com.example.testsecurity.repository;

import com.example.testsecurity.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsUserByUserName(String userName);
    User findByUserName(String userName);
}
