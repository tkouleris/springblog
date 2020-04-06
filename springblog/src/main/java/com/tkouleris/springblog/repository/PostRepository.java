package com.tkouleris.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tkouleris.springblog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long >{

}
