package br.com.libshare.sharingItem;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.libshare.book.BookEntity;
import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "ITEMCOMPARTILHAMENTO")
public class SharingItemEntity extends BaseEntity<SharingItemKey> {

	private static final long serialVersionUID = 201602010251L;

	@NotNull
	@NotEmpty
	@Column(name = "TIPOCOMP", length = 1, nullable = false)
	private String sharingType;

//	@NotEmpty
//	@NotNull
//	@Column(name = "DHCOMP")
//	private Timestamp sharingDateAndHour;

	@Column(name = "DTDEVOL")
	private Timestamp devolutionDate;

	@Column(name = "VLRITEMCOMP", precision = 2, nullable = false)
	private Float sharingItemValue;

//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "CODLIVRO")
//	private BookEntity book;

	@Column(name="CODLIVRO")
	private Long book;

	@Column(name = "OBSERVACAO")
	private String observation;

	@Column(name = "STATUSCOMP")
	private String statusSharing;

	public SharingItemEntity(String sharingType, Timestamp devolutionDate, Float sharingItemValue,
			Long book, String observation) {
		super();
		this.sharingType = sharingType;
		this.devolutionDate = devolutionDate;
		this.sharingItemValue = sharingItemValue;
		this.book = book;
		this.observation = observation;
	}

	public String getSharingType() {
		return sharingType;
	}

	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}

//	public Timestamp getSharingDateAndHour() {
//		return sharingDateAndHour;
//	}
//
//	public void setSharingDateAndHour(Timestamp sharingDateAndHour) {
//		this.sharingDateAndHour = sharingDateAndHour;
//	}

	public Date getDevolutionDate() {
		return devolutionDate;
	}

	public void setDevolutionDate(Timestamp devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public Float getSharingItemValue() {
		return sharingItemValue;
	}

	public void setSharingItemValue(Float sharingItemValue) {
		this.sharingItemValue = sharingItemValue;
	}

	public Long getBook() {
		return book;
	}

	public void setBook(Long book) {
		this.book = book;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getStatusSharing() {
		return statusSharing;
	}

	public void setStatusSharing(String statusSharing) {
		this.statusSharing = statusSharing;
	}
}