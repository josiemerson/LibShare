package br.com.libshare.friends;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.libshare.utils.BaseKey;

@Embeddable
public class FriendsKey extends BaseKey {

	private static final long serialVersionUID = 201602010536L;

	@Column(name = "MEUCODUSU", length = 11, nullable = false)
	private Long myUserCode;

	@Column(name = "CODUSUAMIGO", length = 11, nullable = false)
	private Long userCodeFriend;

	public FriendsKey() {
	}

	public FriendsKey(Long myUserCode, Long userCodeFriend) {
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
		FriendsKey other = (FriendsKey) obj;
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