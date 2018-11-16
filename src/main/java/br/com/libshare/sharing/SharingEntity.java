package br.com.libshare.sharing;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.libshare.sharingItem.SharingItemEntity;
import br.com.libshare.user.UserEntity;
import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "COMPARTILHAMENTO")
@AttributeOverride(name="id", column = @Column(name="CODCOMP"))
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties(ignoreUnknown = true{"hibernateLazyInitializer", "handler"})
public class SharingEntity extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;

	@Column(name = "CODUSUORIGEM")
	private Long userOrigin;

	@Column(name = "CODUSUDESTINO")
	private Long userDestiny;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CODCOMP")
	private List<SharingItemEntity> sharingItem;

//	@NotNull
//	@NotEmpty
//	@Column(name = "TIPOCOMP", length = 1, nullable = false)
//	private String sharingType;

	@Column(name = "DHCOMP")
	private Timestamp sharingDateAndHour;

//	@Column(name = "DTDEVOL")
//	private Timestamp devolutionDate;
//
//	@Column(name = "DTLIMITE")
//	private Timestamp limitDate;


	@Column(name = "VLRCOMP", precision = 2)
	private Float sharingValue;

	public SharingEntity() {
	}

	public SharingEntity(Long userOrigin, Long userDestiny, Timestamp sharingDateAndHour,Float sharingValue) {
		super();
		this.userOrigin = userOrigin;
		this.userDestiny = userDestiny;
//		this.sharingType = sharingType;
		this.sharingDateAndHour = sharingDateAndHour;
//		this.devolutionDate = devolutionDate;
//		this.limitDate = limitDate;
		this.sharingValue = sharingValue;
	}

	public Long getUserOrigin() {
		return userOrigin;
	}

	public void setUserOrigin(Long userOrigin) {
		this.userOrigin = userOrigin;
	}

	public Long getUserDestiny() {
		return userDestiny;
	}

	public void setUserDestiny(Long userDestiny) {
		this.userDestiny = userDestiny;
	}

//	public String getSharingType() {
//		return sharingType;
//	}
//
//	public void setSharingType(String sharingType) {
//		this.sharingType = sharingType;
//	}

	public Timestamp getSharingDateAndHour() {
		return sharingDateAndHour;
	}

	public void setSharingDateAndHour(Timestamp sharingDateAndHour) {
		this.sharingDateAndHour = sharingDateAndHour;
	}

//	public Timestamp getDevolutionDate() {
//		return devolutionDate;
//	}
//
//	public void setDevolutionDate(Timestamp devolutionDate) {
//		this.devolutionDate = devolutionDate;
//	}
//
//	public Timestamp getLimitDate() {
//		return limitDate;
//	}
//
//	public void setLimitDate(Timestamp limitDate) {
//		this.limitDate = limitDate;
//	}

	public Float getSharingValue() {
		return sharingValue;
	}

	public void setSharingValue(Float sharingValue) {
		this.sharingValue = sharingValue;
	}

	public List<SharingItemEntity> getSharingItem() {
		return sharingItem;
	}

	public void setSharingItem(List<SharingItemEntity> sharingItem) {
		this.sharingItem = sharingItem;
	}

	
}