package com.backend.colloboration.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="C_BlogComment")
public class BlogComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@ManyToOne
private Blog blog; 
@ManyToOne
private C_User commentedBy;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Blog getBlog() {
	return blog;
}

public void setBlog(Blog blog) {
	this.blog = blog;
}



public C_User getCommentedBy() {
	return commentedBy;
}


public void setCommentedBy(C_User commentedBy) {
	this.commentedBy = commentedBy;
}

public String getCommentTxt() {
	return commentTxt;
}

public void setCommentTxt(String commentTxt) {
	this.commentTxt = commentTxt;
}

public Date getCommentedOn() {
	return commentedOn;
}
/**
 * @param commentedOn the commentedOn to set
 */
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}
private String commentTxt;
private Date commentedOn;
}
