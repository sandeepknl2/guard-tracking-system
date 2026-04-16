package com.guard.tracking.repository;

import com.guard.tracking.model.Attendance;
import com.guard.tracking.util.ExcelUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExcelRepository {

    public String saveCheckIn(String name, String location) {
        return ExcelUtil.writeCheckIn(name, location);
    }

    public String updateCheckOut(String name, String time, double hours) {
        return ExcelUtil.writeCheckOut(name, time, hours);
    }

    public List<Attendance> getAllRecords() {
        return ExcelUtil.readAll();
    }

    public Attendance findLastCheckIn(String name) {
        return ExcelUtil.findLastRecord(name);
    }
}