package com.guard.tracking.service;

import java.util.List;

import com.guard.tracking.model.Attendance;

public interface AttendanceService {

    String checkIn(String name, String location);

    String checkOut(String name);

    List<Attendance> getAllLogs();

    String getGuardStatus(String name);
}
