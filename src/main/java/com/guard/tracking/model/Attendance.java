package com.guard.tracking.model;

public class Attendance {

    private String guardName;
    private String checkInTime;
    private String checkOutTime;
    private String location;
    private double totalHours;

    // ✅ Default constructor
    public Attendance() {
    }

    // ✅ All args constructor (THIS FIXES YOUR ERROR)
    public Attendance(String guardName, String checkInTime, String checkOutTime,
                      String location, double totalHours) {
        this.guardName = guardName;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.location = location;
        this.totalHours = totalHours;
    }

    // ✅ Getters & Setters
    public String getGuardName() {
        return guardName;
    }

    public void setGuardName(String guardName) {
        this.guardName = guardName;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }
}