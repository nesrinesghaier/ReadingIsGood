package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Role;
import com.getir.readingisgood.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
