package com.backend.colloboration.dao;

import java.util.List;

import com.backend.colloboration.model.Blog;



public interface BlogDao {
	List<Blog> getAllBlog();
	boolean addblog(Blog blog);
    boolean updateblog(Blog blog);
    void deleteblog(Blog blog);
    Blog getBlog(int id);
    List<Blog> getUnApproveBlogs();
}
