package com.backend.colloboration.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="C_Friend")
public class Friend{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@ManyToOne
private C_User fromId;
@ManyToOne
private C_User toId;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public C_User getFromId() {
	return fromId;
}

public void setFromId(C_User fromId) {
	this.fromId = fromId;
}

public C_User getToId() {
	return toId;
}

public void setToId(C_User toId) {
	this.toId = toId;
}

public char getStatus() {
	return status;
}

public void setStatus(char status) {
	this.status = status;
}
private char status;
}
