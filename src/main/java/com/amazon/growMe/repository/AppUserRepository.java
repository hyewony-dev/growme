package com.amazon.growMe.repository;

import com.amazon.growMe.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    List<AppUser> findByEmail(String email);
}
