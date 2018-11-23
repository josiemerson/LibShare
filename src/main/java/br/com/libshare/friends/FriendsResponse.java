package br.com.libshare.friends;

import br.com.libshare.user.UserEntity;

public class FriendsResponse {
	private final FriendsEntity dataFriends;
	private final UserEntity userFriends;

	public FriendsResponse(FriendsEntity friends, UserEntity user) {
		this.dataFriends = friends;
		this.userFriends = user;
	}

	public FriendsEntity getDataFriends() {
		return dataFriends;
	}

	public UserEntity getUserFriends() {
		return userFriends;
	}
}
