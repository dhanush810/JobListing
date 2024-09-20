package com.dhanush.JobListing.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.dhanush.JobListing.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class SearchRepository implements SeachRepo
{
	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter converter;
	
	@Override
	public List<Post> findByText(String tech){
		
		final List<Post> posts = new ArrayList<>();

		
		MongoDatabase db = client.getDatabase("SamplSpringProject");
		MongoCollection<Document> collection = db.getCollection("Courses");

		AggregateIterable<Document> asList = collection.aggregate(Arrays.asList(new Document("$search", 
			    new Document("text", 
			    new Document("query", tech)
			    .append("path", Arrays.asList("techs", "desc", "profile")))), 
			    new Document("$sort", 
			    new Document("exp", 1L)), 
			    new Document("$limit", 5L)));

		asList.forEach(doc -> posts.add(converter.read(Post.class, doc)));

		return posts;
		
	}

	
}
