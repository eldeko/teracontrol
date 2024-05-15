package com.teracontrol.repositories;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teracontrol.models.User;

@ComponentScan

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByKeylock_code(String code);
}
