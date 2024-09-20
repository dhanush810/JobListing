package com.dhanush.JobListing.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dhanush.JobListing.Post;

public interface PostRepositry extends MongoRepository<Post,String>
{
	
}
