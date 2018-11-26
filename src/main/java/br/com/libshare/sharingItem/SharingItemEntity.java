package br.com.libshare.sharingItem;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.libshare.book.BookEntity;
import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "ITEMCOMPARTILHAMENTO")
@AttributeOverride(name="id", column = @Column(name="CODITEMCOMP"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SharingItemEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201602010251L;

	@NotNull
	@Column(name = "CODCOMP")
	private Long sharing;

	@NotNull
	@NotEmpty
	@Column(name = "TIPOCOMP", length = 1, nullable = false)
	private String sharingType;

	@Column(name = "DTDEVOL")
	private Date devolutionDate;

	@Column(name = "VLRITEMCOMP", precision = 2, nullable = false)
	private Float sharingItemValue;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODLIVRO")
	private BookEntity book;

//	@Column(name="CODLIVRO")
//	private Long book;

	@Column(name = "OBSERVACAO")
	private String observation;

	@Column(name = "STATUSCOMP")
	private String statusSharing;

	public SharingItemEntity() {
	}

	public SharingItemEntity(Long sharing, String sharingType, Date devolutionDate, Float sharingItemValue,
			BookEntity book, String observation) {
		super();
		this.sharing = sharing;
		this.sharingType = sharingType;
		this.devolutionDate = devolutionDate;
		this.sharingItemValue = sharingItemValue;
//		this.book = book;
		this.book = book;
		this.observation = observation;
	}

	public Long getSharing() {
		return sharing;
	}

	public void setSharing(Long sharing) {
		this.sharing = sharing;
	}

	public String getSharingType() {
		return sharingType;
	}

	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}

	public Date getDevolutionDate() {
		return devolutionDate;
	}

	public void setDevolutionDate(Date devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public Float getSharingItemValue() {
		return sharingItemValue;
	}

	public void setSharingItemValue(Float sharingItemValue) {
		this.sharingItemValue = sharingItemValue;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}
//	public Long getBook() {
//		return book;
//	}
//	
//	public void setBook(Long book) {
//		this.book = book;
//	}

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