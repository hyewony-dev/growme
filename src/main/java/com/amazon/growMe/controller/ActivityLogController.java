package com.amazon.growMe.controller;

import com.amazon.growMe.dto.ActivityLogDTO;
import com.amazon.growMe.dto.ActivityLogUpdateDTO;
import com.amazon.growMe.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity-logs")
public class ActivityLogController {

    @Autowired
    ActivityLogService activityLogService;

    @PostMapping
    public ResponseEntity logActivity(@RequestBody ActivityLogDTO activityLogDTO) {

        String result = activityLogService.logActivity(activityLogDTO);

        if (result.equals("User or activity does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/{logId}")
    public ResponseEntity deleteActivityLog(@PathVariable Integer logId) {

        String result = activityLogService.deleteActivityLog(logId);

        if (result.equals("Activity log does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PatchMapping("/{logId}")
    public ResponseEntity updateActivityLogNote(
            @PathVariable Integer logId,
            @RequestBody ActivityLogUpdateDTO activityLogUpdateDTO) {

        String result = activityLogService.updateActivityLogNote(logId, activityLogUpdateDTO);

        if (result.equals("Activity log does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}