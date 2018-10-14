package com.backend.colloboration.model;

public class Error {
private int errorcode;
private String errormsg;
public Error(int errorcode, String errormsg) {
	// TODO Auto-generated constructor stub
	super();
	this.errorcode=errorcode;
	this.errormsg=errormsg;
	
}

public int getErrorcode() {
	return errorcode;
}

public void setErrorcode(int errorcode) {
	this.errorcode = errorcode;
}

public String getErrormsg() {
	return errormsg;
}

public void setErrormsg(String errormsg) {
	this.errormsg = errormsg;
}

}
