package com.amazon.growMe.service;


import com.amazon.growMe.dto.ActivityDTO;
import com.amazon.growMe.model.Activity;
import com.amazon.growMe.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public List<ActivityDTO> getAllActivities() {

        List<Activity> activities = activityRepository.findAll();
        List<ActivityDTO> activityDTOS = new ArrayList<>();

        for (Activity activity : activities) {
            ActivityDTO activityDTO = new ActivityDTO();

            activityDTO.setId(activity.getId());
            activityDTO.setName(activity.getName());
            activityDTO.setDescription(activity.getDescription());
            activityDTO.setPoints(activity.getPoints());
            activityDTO.setCategoryId(activity.getCategoryId());

            activityDTOS.add(activityDTO);
        }

        return activityDTOS;
    }
}
