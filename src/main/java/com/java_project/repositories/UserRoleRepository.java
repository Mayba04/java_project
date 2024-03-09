package com.java_project.repositories;

import com.java_project.entities.UserEntity;
import com.java_project.entities.UserRoleEntity;
import com.java_project.entities.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRolePK> {
    List<UserRoleEntity> findByUser(UserEntity user);
}