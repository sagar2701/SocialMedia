package com.backend.colloboration.dao;

import java.util.List;

import com.backend.colloboration.model.BlogComment;

public interface BlogCommentDao {
	void addBlogComment(BlogComment blogComment );
	List<BlogComment> getBlogComments(int blogId);
}
