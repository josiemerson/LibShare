package br.com.libshare.friends;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import br.com.libshare.utils.BaseKey;

@Embeddable
public class FriendsId extends BaseKey implements Serializable{

	@Column(name = "MEUCODUSU")
	private Long myUserCode;

	@Column(name = "CODUSUAMIGO")
	private Long userCodeFriend;

	public FriendsId() {
	}

	public FriendsId(Long myUserCode, Long userCodeFriend) {
		super();
		this.myUserCode = myUserCode;
		this.userCodeFriend = userCodeFriend;
	}

	public Long getMyUserCode() {
		return myUserCode;
	}

	public void setMyUserCode(Long myUserCode) {
		this.myUserCode = myUserCode;
	}

	public Long getUserCodeFriend() {
		return userCodeFriend;
	}

	public void setUserCodeFriend(Long userCodeFriend) {
		this.userCodeFriend = userCodeFriend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myUserCode == null) ? 0 : myUserCode.hashCode());
		result = prime * result + ((userCodeFriend == null) ? 0 : userCodeFriend.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendsId other = (FriendsId) obj;
		if (myUserCode == null) {
			if (other.myUserCode != null)
				return false;
		} else if (!myUserCode.equals(other.myUserCode))
			return false;
		if (userCodeFriend == null) {
			if (other.userCodeFriend != null)
				return false;
		} else if (!userCodeFriend.equals(other.userCodeFriend))
			return false;
		return true;
	}
}