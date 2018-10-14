package com.backend.colloboration.dao;

import java.util.List;

import com.backend.colloboration.model.Job;

public interface JobDao {
	void saveJob(Job job);
	List<Job>   getAllJobs();
}
