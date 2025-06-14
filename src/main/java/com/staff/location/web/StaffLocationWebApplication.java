package com.staff.location.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
@RestController
public class StaffLocationWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffLocationWebApplication.class, args);
    }

    @GetMapping("/staff-details-web/{firstName}")
    public ResponseEntity<String> getFormattedStaffDetails(@PathVariable String firstName) {
        String url = "http://localhost:8081/staff-details/" + firstName;

        RestClient restClient = RestClient.create();

        try {
            // Call the Staff Location API
            StaffDetails[] staffList = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(StaffDetails[].class);

            // Check if empty
            if (staffList.length == 0) {
                return ResponseEntity.ok("No staff found");
            }

            // Format response
            StringBuilder output = new StringBuilder();
            for (StaffDetails staff : staffList) {
                output.append("o Name: ").append(staff.getName()).append("<br>");
                output.append("o Employee ID: ").append(staff.getEmployeeId()).append("<br>");
                output.append("o Location: ").append(staff.getOfficeLocation()).append("<br>");
                output.append("o Phone: ").append(staff.getOfficePhone()).append("<br><br>");
            }

            return ResponseEntity.ok(output.toString());

        } catch (HttpClientErrorException e) {
            // Forward exact JSON error from staff-location-api
            String responseBody = e.getResponseBodyAsString();
            if (responseBody != null && responseBody.contains("invalid characters")) {
                // Show plain error message if invalid characters
                return ResponseEntity.ok("Error: invalid characters");
            }
            return ResponseEntity.status(e.getStatusCode())
                    .header("Content-Type", "application/json")
                    .body(responseBody);

        } catch (RestClientException e) {
            // Catch unexpected issues like connection errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-Type", "application/json")
                    .body("{\"error\":\"Something went wrong contacting staff-location-api\"}");
        }
    }
}
