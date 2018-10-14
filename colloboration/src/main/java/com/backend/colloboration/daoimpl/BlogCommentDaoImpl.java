package com.backend.colloboration.daoimpl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.backend.colloboration.dao.BlogCommentDao;
import com.backend.colloboration.model.BlogComment;
@Repository
@Transactional
public class BlogCommentDaoImpl implements BlogCommentDao{
	@Autowired
	private SessionFactory sessionFactory;

	public void addBlogComment(BlogComment blogComment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogComment);
		
	}

	public List<BlogComment> getBlogComments(int blogId) {
		Session session=sessionFactory.getCurrentSession();
		//BlogComment  is an Entity
		// id,blogPost,user,commentTxt, commentedOn
		Query query=session.createQuery("from BlogComment where blog.Blogid=?");
		query.setInteger(0, blogId);
		List<BlogComment> blogComments=query.list();
		return blogComments;
	}
}
