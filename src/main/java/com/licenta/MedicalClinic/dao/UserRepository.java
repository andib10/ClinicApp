package com.licenta.MedicalClinic.dao;

import com.licenta.MedicalClinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
