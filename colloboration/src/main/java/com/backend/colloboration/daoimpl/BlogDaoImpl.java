package com.backend.colloboration.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.colloboration.dao.BlogDao;
import com.backend.colloboration.model.Blog;
@Transactional
@Repository("blogDao")

public class BlogDaoImpl implements BlogDao {
	private static final Logger log=LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;		

	public BlogDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public boolean addblog(Blog blog) {
		Session s=sessionFactory.getCurrentSession();
		try {
		s.save(blog);
		log.debug("blog added succefully");
		return true;
		}
		catch(HibernateException e) {e.printStackTrace(); 
		log.error(e.getMessage());
		return false;}
	}

	public boolean updateblog(Blog blog) {
		Session s=sessionFactory.getCurrentSession();
		s.update(blog);
		return true;
	}

	public void deleteblog(Blog blog) {
		Session s=sessionFactory.getCurrentSession();	
		
		s.delete(blog);
		
	}

	public Blog getBlog(int id) {
		Session s=sessionFactory.getCurrentSession();
		
	    try
	    {
	    	Blog blog=(Blog)s.get(Blog.class,id);
	    	return blog;
	    }
	    catch(HibernateException e) {e.printStackTrace();}
	    	return null;
	}

	public List<Blog> getAllBlog() {
		Session s=sessionFactory.getCurrentSession();
		Query q=s.createQuery("from Blog where approvalStatus=true");
		List<Blog> blog=q.list();
		return blog;
	}

	public List<Blog> getUnApproveBlogs() {
		Session s=sessionFactory.getCurrentSession();
		Query q=s.createQuery("from Blog where approvalStatus=false");
		List<Blog> blog=q.list();
		return blog;
	}

}
