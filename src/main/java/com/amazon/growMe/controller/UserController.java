package com.amazon.growMe.controller;

import com.amazon.growMe.dto.ActivityLogHistoryDTO;
import com.amazon.growMe.dto.ProgressDTO;
import com.amazon.growMe.dto.UserDTO;
import com.amazon.growMe.service.ActivityLogService;
import com.amazon.growMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {

        String result = userService.createUser(userDTO);

        if (result.equals("User already exists")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @Autowired
    ActivityLogService activityLogService;

    @GetMapping("/{userId}/activity-logs")
    public ResponseEntity getActivityLogHistory(@PathVariable Integer userId) {

        List<ActivityLogHistoryDTO> history = activityLogService.getActivityLogHistory(userId);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }
    @GetMapping("/{userId}/progress")
    public ResponseEntity getUserProgress(@PathVariable Integer userId) {

        ProgressDTO progress = userService.getUserProgress(userId);

        return ResponseEntity.status(HttpStatus.OK).body(progress);
    }
}