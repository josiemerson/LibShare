package br.com.libshare.friends;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "amigos")
public class FriendsEntity extends BaseEntity<FriendsKey> {

	private static final long serialVersionUID = 201602010251L;

	@Column(name = "STATUS")
	private String statusFriend;

//	@OneToOne(fetch = FetchType.EAGER)
//	@Column(name = "meuCodUsu")
//	@JoinTable(name = "usuario", joinColumns = @JoinColumn(name = "CODUSU"))
//	private UserEntity meuCodUsu;
//	
//	@OneToOne
//	@JoinTable(name = "usuario", joinColumns = {@JoinColumn(name = "CODUSU")})
//	@Column(name = "codUsuAmigo")
//	private UserEntity codUsuAmigo;
}