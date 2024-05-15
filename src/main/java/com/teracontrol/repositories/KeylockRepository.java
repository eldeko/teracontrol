package com.teracontrol.repositories;

import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teracontrol.models.Keylock;

@ComponentScan
@Repository
public interface KeylockRepository extends JpaRepository<Keylock, Long> {

    Keylock findByCode(String code);
    
    
}