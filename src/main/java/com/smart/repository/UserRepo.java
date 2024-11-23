package com.smart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
