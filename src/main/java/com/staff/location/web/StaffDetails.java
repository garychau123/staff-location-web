package com.staff.location.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StaffDetails {
    private String name;
    @JsonProperty("id") // Change to match the actual JSON property name
    private String id;
    private String officeLocation;
    private String officePhone;

    public StaffDetails() {}

    public String getName() {
        return name;
    }

    public String getEmployeeId() {
        return id;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public String getOfficePhone() {
        return officePhone;
    }
}
