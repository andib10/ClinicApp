package com.licenta.MedicalClinic.dao;

import com.licenta.MedicalClinic.entity.Role;

import java.util.List;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

    List<Role> findAll();
}
