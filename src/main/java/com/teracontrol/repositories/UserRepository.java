package com.teracontrol.repositories;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teracontrol.models.User;

@ComponentScan

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.keyLock.keylockCode = :keylockCode")
    User findByKeylockCode(@Param("keylockCode") String keylockCode);
}
