package com.backend.colloboration.dao;

import java.util.List;

import com.backend.colloboration.model.C_User;
import com.backend.colloboration.model.Friend;

public interface FriendDao {
	List<C_User> getSuggestedUsers(String email);

	void addFriendRequest(Friend friend);

	List<Friend> getPendingRequests(String email);

	void acceptFriendRequest(Friend friend);

	void deleteFriendRequest(Friend friend);

	List<C_User> listOfFriends(String email);
}
