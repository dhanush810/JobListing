package com.dhanush.JobListing.Repository;

import java.util.List;


import com.dhanush.JobListing.Post;


public interface SeachRepo {
	List<Post> findByText(String tech);
}

