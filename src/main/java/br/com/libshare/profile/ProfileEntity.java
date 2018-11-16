package br.com.libshare.profile;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "perfil")
@AttributeOverride(name="id", column = @Column(name="CODPERFIL"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProfileEntity extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ativo", nullable = false)
	private String active;

	@NotNull
	@Column(name="codusu", nullable = false)
	private Long codUsu;

	@NotNull
	@Size(min = 4, max = 50)
	@Column(name = "nome", length = 120, nullable = false)
	private String name;

	@NotNull
	@Size(min = 4, max = 120)
	@Column(name = "sobrenome", length = 120, nullable = false)
	private String lastName;

	@NotNull
	@Size(min = 4, max = 255)
	@Column(name = "endereco", length = 255, nullable = false)
	private String address;
	
	@NotNull
	@Size(min = 4, max = 50)
	@Column(name = "bairro", length = 50, nullable = false)
	private String neighborhood;
	
	@NotNull
	@Size(min = 4, max = 50)
	@Column(name = "cidade", length = 50, nullable = false)
	private String city;
	
	@NotNull
	@Size(min = 4, max = 50)
	@Column(name = "estado", nullable = false)
	private String state;
	
	@NotNull
	@Size(min = 4, max = 50)
	@Column(name = "pais", length = 50, nullable = false)
	private String country;
	
	@NotNull
	@Column(name = "dtnascimento", nullable = false)
	private Timestamp dateBirth;

	@NotNull
	@Column(name = "nro", length = 11, nullable = false)
	private Integer number;
	
	@NotNull
	@Size(min = 4, max = 120)
	@Column(name = "complemento", length = 120, nullable = false)
	private String complement;
	
	@NotNull
	@Size(min = 1, max = 16)
	@Column(name = "telefone", length = 16, nullable = false)
	private String telephone;

	@NotNull
	@Size(min = 8, max = 10)
	@Column(name = "cep")
	private String cep;

	@Column(name = "pathfoto")
	private String pathFoto;

	@Column(name = "regiao")
	private String region;

	@Column(name = "permitefone")
	private String allowShowPhone;

//	@Transient
//	private String fileNameImg;
	
//	@Column(columnDefinition="LONGBLOB")
//	private byte[] imgbase64;

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "usuario_livro", joinColumns = @JoinColumn(name = "codusu"), inverseJoinColumns = @JoinColumn(name = "codlivro") )
//	private List<UserBookEntity> usersBooks; 

	public ProfileEntity() {
	}

	public ProfileEntity(String active, String name, String lastName, String address, String neighborhood, String city,
			String state, String country, Timestamp dateBirth, Integer number, String complement, String telephone,
			String cep, String pathFoto, String latLong) {
		super();
		this.active = active;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.dateBirth = dateBirth;
		this.number = number;
		this.complement = complement;
		this.telephone = telephone;
		this.cep = cep;
		this.pathFoto = pathFoto;
	}

	public String getActive() {
		return active;
	}
	
	public Long getCodUsu() {
		return codUsu;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public Timestamp getDateBirth() {
		return dateBirth;
	}

	public Integer getNumber() {
		return number;
	}

	public String getComplement() {
		return complement;
	}

	public String getTelephone() {
		return telephone;
	}
	
	public String getCep() {
		return cep;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public String getRegion() {
		return region;
	}

	public String getAllowShowPhone() {
		return allowShowPhone;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setCodUsu(Long codUsu) {
		this.codUsu = codUsu;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setDateBirth(Timestamp dateBirth) {
		this.dateBirth = dateBirth;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setAllowShowPhone(String allowShowPhone) {
		this.allowShowPhone = allowShowPhone;
	}

//	@Transient
//	public String getFileNameImg() {
//		return fileNameImg;
//	}
//
//	@Transient
//	public void setFileNameImg(String fileNameImg) {
//		this.fileNameImg = fileNameImg;
//	}

//	public byte[] getImgbase64() {
//		return imgbase64;
//	}
//
//	public void setImgbase64(byte[] imgbase64) {
//		this.imgbase64 = imgbase64;
//	}
}