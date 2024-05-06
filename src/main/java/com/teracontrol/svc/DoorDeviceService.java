package com.teracontrol.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.teracontrol.models.DoorControlDevice;
import com.teracontrol.repositories.DoorControlDeviceRepository;


@Service
public class DoorDeviceService {

    @Autowired
    private DoorControlDeviceRepository doorControlDeviceRepository;

    DoorControlDevice getDoorByDeviceSerialNumber(String serialNumber) {
        
    DoorControlDevice result = doorControlDeviceRepository.findBySerialNumber(serialNumber);
        return result;
    }
}
