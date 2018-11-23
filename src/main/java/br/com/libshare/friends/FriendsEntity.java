package br.com.libshare.friends;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.libshare.user.UserEntity;
import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "amigos")
@AttributeOverride(name="id", column = @Column(name="ID"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FriendsEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201602010251L;

	@Column(name = "STATUS")
	private String statusFriend;

//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "MEUCODUSU")
//	private UserEntity myUserCode;
	@Column(name = "MEUCODUSU")
	private Long myUserCode;

//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "CODUSUAMIGO")
//	private UserEntity userCodeFriend;
	@Column(name = "CODUSUAMIGO")
	private Long userCodeFriend;

	public FriendsEntity() {
	}
	
//	public FriendsEntity(UserEntity myUserCode, UserEntity userCodeFriend, String statusFriend) {
//		super();
//		this.statusFriend = statusFriend;
//		this.myUserCode = myUserCode;
//		this.userCodeFriend = userCodeFriend;
//	}
	public FriendsEntity(Long myUserCode, Long userCodeFriend, String statusFriend) {
		super();
		this.statusFriend = statusFriend;
		this.myUserCode = myUserCode;
		this.userCodeFriend = userCodeFriend;
	}

	public String getStatusFriend() {
		return statusFriend;
	}

	public void setStatusFriend(String statusFriend) {
		this.statusFriend = statusFriend;
	}

//	public UserEntity getMyUserCode() {
//		return myUserCode;
//	}
//
//	public void setMyUserCode(UserEntity myUserCode) {
//		this.myUserCode = myUserCode;
//	}
//
//	public UserEntity getUserCodeFriend() {
//		return userCodeFriend;
//	}
//
//	public void setUserCodeFriend(UserEntity userCodeFriend) {
//		this.userCodeFriend = userCodeFriend;
//	}
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
}