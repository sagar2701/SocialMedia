package com.backend.colloboration.dao;
import java.util.List;

import com.backend.colloboration.model.*;
public interface UserDao {
	List<C_User> getAllUsers();
	
    boolean addUser(C_User user);
    boolean updateUser(C_User user);
   // void deleteUser(int id);
    C_User getUser(String email);
    boolean getByemailId(String emailId);
    C_User login(C_User user);
    boolean isEmailUnique(String email);
}
