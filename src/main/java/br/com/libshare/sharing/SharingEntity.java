package br.com.libshare.sharing;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.libshare.sharingItem.SharingItemEntity;
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

	@Column(name = "DHCOMP")
	private LocalDateTime sharingDateAndHour;

	@Column(name = "VLRCOMP", precision = 2)
	private Float sharingValue;

	public SharingEntity() {
	}

	public SharingEntity(Long userOrigin, Long userDestiny, LocalDateTime sharingDateAndHour,Float sharingValue, List<SharingItemEntity> sharingItem) {
		super();
		this.userOrigin = userOrigin;
		this.userDestiny = userDestiny;
		this.sharingDateAndHour = sharingDateAndHour;
		this.sharingValue = sharingValue;
		this.sharingItem = sharingItem;
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

	public LocalDateTime getSharingDateAndHour() {
		return sharingDateAndHour;
	}

	public void setSharingDateAndHour(LocalDateTime sharingDateAndHour) {
		this.sharingDateAndHour = sharingDateAndHour;
	}

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