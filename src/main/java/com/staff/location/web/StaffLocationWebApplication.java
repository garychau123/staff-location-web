package com.staff.location.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;   

import java.util.List;

@SpringBootApplication
@RestController
public class StaffLocationWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffLocationWebApplication.class, args);
    }

    // @RequestMapping("/")
    // public String home() {
    //     return "Staff Location Web";
    // }

    @GetMapping("/staff-details-web/{firstName}")
    public ResponseEntity<String> getFormattedStaffDetails(@PathVariable String firstName) {
        // 👇 Call the staff-location-api (not employee-service)
        String url = "http://localhost:8081/staff-details/" + firstName;

        RestClient restClient = RestClient.create();
        StaffDetails[] staffList = restClient.get()
                .uri(url)
                .retrieve()
                .body(StaffDetails[].class);

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < staffList.length; i++) {
            StaffDetails staff = staffList[i];
            output.append("o Name: ").append(staff.getName()).append("<br>");
            output.append("o Employee ID: ").append(staff.getEmployeeId()).append("<br>");
            output.append("o Location: ").append(staff.getOfficeLocation()).append("<br>");
            output.append("o Phone: ").append(staff.getOfficePhone()).append("<br><br>");
        }

        return ResponseEntity.ok(output.toString());
    }
}

