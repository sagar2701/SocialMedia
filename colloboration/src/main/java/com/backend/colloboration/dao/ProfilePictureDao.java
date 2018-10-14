package com.backend.colloboration.dao;
import com.backend.colloboration.model.ProfilePicture;
public interface ProfilePictureDao {
	void uploadProfilePicture(ProfilePicture profilePicture);
	ProfilePicture  getProfilePicture(String email);
}
