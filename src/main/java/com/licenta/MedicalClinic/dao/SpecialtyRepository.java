package com.licenta.MedicalClinic.dao;

import com.licenta.MedicalClinic.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
}
