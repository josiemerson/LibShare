package br.com.libshare.user;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.libshare.friends.FriendsEntity;
//import br.com.libshare.friends.FriendsEntity;
import br.com.libshare.permission.PermissionEntity;
import br.com.libshare.profile.ProfileEntity;
import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "usuario")
@AttributeOverride(name="id", column = @Column(name="CODUSU"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;

	@Email
	@NotNull
	@NotEmpty
	@Column(name = "email", length = 255, nullable = false, unique = true)
	private String email;

	@NotNull
	@Size(min = 4, max = 80)
	@Column(name = "senha", length = 80, nullable = false)
	private String password;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CODUSU")
	private ProfileEntity profile;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuariopermissao", joinColumns = @JoinColumn(name = "CODUSU"), inverseJoinColumns = @JoinColumn(name = "CODPERMISSAO") )
	private List<PermissionEntity> permissions;

	public UserEntity() {
	}

	public UserEntity(String email, String password, ProfileEntity profile, List<PermissionEntity> permissions) {
		super();
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.permissions = permissions;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	public List<PermissionEntity> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionEntity> permissions) {
		this.permissions = permissions;
	}
}