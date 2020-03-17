package com.tkouleris.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tkouleris.springblog.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
