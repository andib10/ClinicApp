package com.licenta.MedicalClinic.dao;

import com.licenta.MedicalClinic.entity.Patient;
import com.licenta.MedicalClinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByUserId(int userId);
}
