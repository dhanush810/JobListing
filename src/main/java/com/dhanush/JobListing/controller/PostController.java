package com.dhanush.JobListing.controller;

import java.io.IOException;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.JobListing.Post;
import com.dhanush.JobListing.Repository.PostRepositry;
import com.dhanush.JobListing.Repository.SeachRepo;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
	
	@Autowired
	PostRepositry repo;
	
	@Autowired
	SeachRepo srepo;
	
	
	@ApiIgnore
	@RequestMapping(value = "/")
	public void Redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}
	
	
	@GetMapping("/allPosts")
	public List<Post> getdata(){
		return repo.findAll();
	}
	
	
	@PostMapping("/post")
	public Post adddata(@RequestBody Post post) {
		return repo.save(post);
	}
	
	
	@GetMapping("/posts/{text}")
	public List<Post> search(@PathVariable String text){
		return srepo.findByText(text);
	}
}
