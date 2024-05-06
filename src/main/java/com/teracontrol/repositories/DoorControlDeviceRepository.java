package com.teracontrol.repositories;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.teracontrol.models.DoorControlDevice;

import org.springframework.data.jpa.repository.JpaRepository;

@ComponentScan

@Repository
public interface DoorControlDeviceRepository extends JpaRepository<DoorControlDevice, Long>{

    DoorControlDevice findBySerialNumber(String SerialNumber);    

}
