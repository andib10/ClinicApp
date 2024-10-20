package com.licenta.MedicalClinic.service;

import com.licenta.MedicalClinic.entity.Specialty;

import java.util.List;

public interface SpecialtyService {

    List<Specialty> findAll();

    Specialty findById(int theId);

    Specialty save(Specialty theSpecialty);

    void deleteById(int theId);
}
