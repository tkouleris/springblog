package com.tkouleris.springblog.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.tkouleris.springblog.Exception.PostNotFoundException;
import com.tkouleris.springblog.dto.PostDto;
import com.tkouleris.springblog.model.Post;
import com.tkouleris.springblog.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private AuthService authService;
	@Autowired
	private PostRepository postRepository;
	
	public void createPost(PostDto postDto)
	{
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		User user = authService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("No User looged in"));
		post.setUsername(user.getUsername());
		post.setCreatedOn(Instant.now());
		postRepository.save(post);
	}

	public List<PostDto> showAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
	}

	public PostDto readSinglePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("For id " + id));
		return mapFromPostToDto(post);
	}
	
	private PostDto mapFromPostToDto(Post post)
	{
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setUsername(post.getUsername());
		return postDto;
	}
	
	private Post mapFromDtoToPost(PostDto postDto)
	{
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		User loggedInUser = authService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("User not found"));
		post.setUpdatedOn(Instant.now());
		
		return post;
	}
}
