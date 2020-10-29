package com.bharath.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.springcloud.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
