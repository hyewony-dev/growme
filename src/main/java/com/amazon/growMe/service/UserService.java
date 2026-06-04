package com.amazon.growMe.service;

import com.amazon.growMe.dto.ProgressDTO;
import com.amazon.growMe.dto.UserDTO;
import com.amazon.growMe.model.AppUser;
import com.amazon.growMe.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    AppUserRepository appUserRepository;

    public String createUser(UserDTO userDTO) {

        List<AppUser> users = appUserRepository.findAll();

        for (AppUser user : users) {
            if (user.getEmail().equals(userDTO.getEmail())) {
                return "User already exists";
            }
        }

        AppUser newUser = new AppUser(
                null,
                userDTO.getUserName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                0,
                1
        );

        appUserRepository.save(newUser);

        return "User was created";
    }
    public ProgressDTO getUserProgress(Integer userId) {

        AppUser user = appUserRepository.findById(userId).get();

        ProgressDTO progressDTO = new ProgressDTO();

        progressDTO.setUserName(user.getUserName());
        progressDTO.setTotalPoints(user.getTotalPoints());
        progressDTO.setLevel(user.getLevel());

        return progressDTO;
    }
}
