package com.amazon.growMe.service;

import com.amazon.growMe.dto.ActivityLogDTO;
import com.amazon.growMe.dto.ActivityLogHistoryDTO;
import com.amazon.growMe.dto.ActivityLogUpdateDTO;
import com.amazon.growMe.model.Activity;
import com.amazon.growMe.model.ActivityLog;
import com.amazon.growMe.model.AppUser;
import com.amazon.growMe.repository.ActivityLogRepository;
import com.amazon.growMe.repository.ActivityRepository;
import com.amazon.growMe.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityLogService {

    @Autowired
    ActivityLogRepository activityLogRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public String logActivity(ActivityLogDTO activityLogDTO) {

        Optional<AppUser> userOptional = appUserRepository.findById(activityLogDTO.getUserId());
        Optional<Activity> activityOptional = activityRepository.findById(activityLogDTO.getActivityId());

        if (userOptional.isEmpty() || activityOptional.isEmpty()) {
            return "User or activity does not exist";
        }

        AppUser user = userOptional.get();
        Activity activity = activityOptional.get();

        ActivityLog activityLog = new ActivityLog(
                null,
                user.getId(),
                activity.getId(),
                activity.getPoints(),
                activityLogDTO.getNote(),
                LocalDateTime.now()
        );

        activityLogRepository.save(activityLog);

        Integer newTotalPoints = user.getTotalPoints() + activity.getPoints();
        user.setTotalPoints(newTotalPoints);
        user.setLevel(newTotalPoints / 100 + 1);

        appUserRepository.save(user);

        return "Activity was logged. Points were added.";
    }
    public List<ActivityLogHistoryDTO> getActivityLogHistory(Integer userId) {

        List<ActivityLog> logs = activityLogRepository.findByUserId(userId);
        List<ActivityLogHistoryDTO> history = new ArrayList<>();

        for (ActivityLog log : logs) {

            ActivityLogHistoryDTO dto = new ActivityLogHistoryDTO();

            Activity activity = activityRepository.findById(log.getActivityId()).get();

            dto.setActivityName(activity.getName());
            dto.setEarnedPoints(log.getEarnedPoints());
            dto.setNote(log.getNote());
            dto.setLoggedAt(log.getLoggedAt().toString());
            history.add(dto);
        }

        return history;
    }
    public String deleteActivityLog(Integer logId) {

        if (activityLogRepository.findById(logId).isEmpty()) {
            return "Activity log does not exist";
        }

        ActivityLog log = activityLogRepository.findById(logId).get();

        AppUser user = appUserRepository.findById(log.getUserId()).get();

        Integer newTotalPoints = user.getTotalPoints() - log.getEarnedPoints();

        if (newTotalPoints < 0) {
            newTotalPoints = 0;
        }

        user.setTotalPoints(newTotalPoints);
        user.setLevel(newTotalPoints / 100 + 1);

        appUserRepository.save(user);

        activityLogRepository.deleteById(logId);

        return "Activity log was deleted. Points were subtracted.";
    }
    public String updateActivityLogNote(Integer logId, ActivityLogUpdateDTO activityLogUpdateDTO) {

        if (activityLogRepository.findById(logId).isEmpty()) {
            return "Activity log does not exist";
        }

        ActivityLog activityLog = activityLogRepository.findById(logId).get();

        activityLog.setNote(activityLogUpdateDTO.getNote());

        activityLogRepository.save(activityLog);

        return "Activity log was updated";
    }
}