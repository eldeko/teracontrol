package com.teracontrol.repositories;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teracontrol.models.KeyLock;

@ComponentScan
@Repository
public interface KeylockRepository extends JpaRepository<KeyLock, Long> {

    
}