package com.backend.colloboration.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="C_Blog")
public class Blog {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private int Blogid;
	private String Blogname;
	@Lob
	private String BlogContent;

 private Date BlogCreation;
 @ManyToOne
 private C_User postedby;
 private int likes;
	
public C_User getPostedby() {
	return postedby;
}


public void setPostedby(C_User postedby) {
	this.postedby = postedby;
}


public int getLikes() {
	return likes;
}


public void setLikes(int likes) {
	this.likes = likes;
}


public boolean isApprovalStatus() {
	return approvalStatus;
}


public void setApprovalStatus(boolean approvalStatus) {
	this.approvalStatus = approvalStatus;
}
	private boolean approvalStatus; 
	public int getBlogid() {
		return Blogid;
	}
	
	public void setBlogid(int blogid) {
		Blogid = blogid;
	}
	
	public String getBlogname() {
		return Blogname;
	}
	
	public void setBlogname(String blogname) {
		Blogname = blogname;
	}
	
	public String getBlogContent() {
		return BlogContent;
	}
	
	public void setBlogContent(String blogContent) {
		BlogContent = blogContent;
	}
	 
	/**
	 * @return the blogCreation
	 */
	public Date getBlogCreation() {
		return BlogCreation;
	}

	/**
	 * @param blogCreation the blogCreation to set
	 */
	public void setBlogCreation(Date blogCreation) {
		BlogCreation = blogCreation;
	}

	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	private String userid;
	public Blog() {


		this.userid = "USER" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}
	

}
