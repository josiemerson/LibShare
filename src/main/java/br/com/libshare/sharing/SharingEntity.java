package br.com.libshare.sharing;

import java.time.LocalDateTime;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.jmx.snmp.Timestamp;

import br.com.libshare.profile.ProfileEntity;
import br.com.libshare.sharingItem.SharingItemEntity;
import br.com.libshare.user.UserEntity;
import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "COMPARTILHAMENTO")
@AttributeOverride(name="id", column = @Column(name="CODCOMP"))
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SharingEntity extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;

//	@Column(name = "CODUSUORIGEM")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODUSUORIGEM")
	private UserEntity userOrigin;

//	@Column(name = "CODUSUDESTINO")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODUSUDESTINO")
	private UserEntity userDestiny;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CODCOMP")
	private List<SharingItemEntity> sharingItem;

	@Column(name = "DHCOMP")
	private Date sharingDateAndHour;

	@Column(name = "VLRCOMP")
	private Float sharingValue;

	public SharingEntity() {
	}

	public SharingEntity(UserEntity userOrigin, UserEntity userDestiny
			, Date sharingDateAndHour
			,Float sharingValue
			, List<SharingItemEntity> sharingItem
			) {
		super();
		this.userOrigin = userOrigin;
		this.userDestiny = userDestiny;
		this.sharingDateAndHour = sharingDateAndHour;
		this.sharingValue = sharingValue;
		this.sharingItem = sharingItem;
	}

	public UserEntity getUserOrigin() {
		return userOrigin;
	}

	public void setUserOrigin(UserEntity userOrigin) {
		this.userOrigin = userOrigin;
	}

	public UserEntity getUserDestiny() {
		return userDestiny;
	}

	public void setUserDestiny(UserEntity userDestiny) {
		this.userDestiny = userDestiny;
	}

	public Date getSharingDateAndHour() {
		return sharingDateAndHour;
	}

	public void setSharingDateAndHour(Date sharingDateAndHour) {
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