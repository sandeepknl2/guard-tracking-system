package com.guard.tracking.controller;

import com.guard.tracking.model.Attendance;
import com.guard.tracking.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    // ✅ CHECK-IN API
    @PostMapping("/checkin")
    public String checkIn(@RequestParam("name") String name,
                          @RequestParam("location") String location) {

        return service.checkIn(name, location);
    }

    // ✅ CHECK-OUT API
    @PostMapping("/checkout")
    public String checkOut(@RequestParam("name") String name) {

        return service.checkOut(name);
    }

    // ✅ GET ALL LOGS
    @GetMapping("/logs")
    public List<Attendance> getLogs() {

        return service.getAllLogs();
    }

    // ✅ GET GUARD STATUS
    @GetMapping("/status")
    public String getStatus(@RequestParam("name") String name) {

        return service.getGuardStatus(name);
    }
}