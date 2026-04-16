package com.guard.tracking.serviceImpl;

import com.guard.tracking.model.Attendance;
import com.guard.tracking.repository.ExcelRepository;
import com.guard.tracking.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private ExcelRepository repository;

    // ✅ CHECK-IN (Correct)
    @Override
    public String checkIn(String name, String location) {
        return repository.saveCheckIn(name, location);
    }

    // ✅ CHECK-OUT (Correct)
    @Override
    public String checkOut(String name) {

        Attendance record = repository.findLastCheckIn(name);

        if (record == null || record.getCheckInTime() == null) {
            return "No valid check-in found!";
        }

        try {
            LocalDateTime checkInTime = LocalDateTime.parse(record.getCheckInTime());
            LocalDateTime checkOutTime = LocalDateTime.now();

            Duration duration = Duration.between(checkInTime, checkOutTime);
            double hours = duration.toMinutes() / 60.0;

            return repository.updateCheckOut(name, checkOutTime.toString(), hours);

        } catch (Exception e) {
            return "Error parsing time!";
        }
    }

    // ✅ GET LOGS
    @Override
    public List<Attendance> getAllLogs() {
        return repository.getAllRecords();
    }

    // ✅ STATUS
    @Override
    public String getGuardStatus(String name) {

        Attendance record = repository.findLastCheckIn(name);

        if (record == null) {
            return "OUT";
        }

        if (record.getCheckOutTime() == null || record.getCheckOutTime().isEmpty()) {
            return "IN";
        }

        return "OUT";
    }
}