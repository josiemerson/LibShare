package br.com.libshare.permission;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "permissao")
@AttributeOverride(name = "id", column = @Column(name = "codpermissao"))
public class PermissionEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201602010401L;

	@NotNull
	@NotEmpty
	@Size(max = 45)
	@Column(name = "papel", length = 45, nullable = false, unique = true)
	private String role;

	public PermissionEntity() {
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}