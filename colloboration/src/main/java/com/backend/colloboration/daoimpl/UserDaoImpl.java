package com.backend.colloboration.daoimpl;
import com.backend.colloboration.dao.*;
import com.backend.colloboration.model.*;
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

@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao {
private static final Logger log=LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;		

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	public List<C_User> getAllUsers() {
		Session s=sessionFactory.getCurrentSession();
		Query q=s.createQuery("from C_User");
		List<C_User> users=q.list();
		return users;
		
				
	}      
	
	public boolean addUser(C_User user) {
		Session s=sessionFactory.getCurrentSession();
		try {
		s.save(user);
		log.debug("user added succefully");
		return true;
		}
		catch(HibernateException e) {e.printStackTrace(); 
		log.error(e.getMessage());
		return false;}
	}
    public boolean updateUser(C_User user) {
    	Session s=sessionFactory.getCurrentSession();
    	s.update(user);
	return true;
	}

    public C_User getUser(String email)
    {Session s=sessionFactory.getCurrentSession();
    
    	C_User user=s.get(C_User.class,email);
    	return user;
    
    }
	/*public void deleteUser(int id) {
		Session s=sessionFactory.getCurrentSession();	
		C_User user=getUser(email);
		s.delete(user);*/
		
	//}

	public boolean getByemailId(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from C_User where email=?");
		query.setString(0, email);
		C_User user=(C_User)query.uniqueResult();
		if(user!=null){//duplicate email address
			return false;
		}else{//unique email address
			return true;
		}
	}
		public C_User login(C_User user) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from C_User where email=? and password=?");
			query.setString(0, user.getEmail());
			query.setString(1, user.getPassword());
			return (C_User)query.uniqueResult();
		}
		public boolean isEmailUnique(String email) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from User where email=?");
			query.setString(0, email);
			C_User user=(C_User)query.uniqueResult();
			if(user!=null){//duplicate email address
				return false;
			}else{//unique email address
				return true;
			}
		
	
}
}

