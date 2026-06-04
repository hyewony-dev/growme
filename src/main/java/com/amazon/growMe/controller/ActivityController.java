package com.amazon.growMe.controller;

import com.amazon.growMe.dto.ActivityDTO;
import com.amazon.growMe.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @GetMapping
    public ResponseEntity getAllActivities() {

        List<ActivityDTO> activities = activityService.getAllActivities();

        if (activities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No activities found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }
}