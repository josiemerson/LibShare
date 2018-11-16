package br.com.libshare.book;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.libshare.user.UserEntity;
import br.com.libshare.utils.BaseEntity;
import br.com.libshare.utils.database.BookStatus;

@Entity
@Table(name = "livro")
@AttributeOverride(name="id", column = @Column(name="CODLIVRO"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookEntity extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Column(name = "NOME", length = 50, nullable = false)
	private String name;

	@NotNull
	@Column(name = "AUTOR", length = 50, nullable = false)
	private String author;

	@NotNull
	@Column(name = "EDITORA", length = 50, nullable = false)
	private String publishingCompany;

	@NotNull
	@Column(name = "PATHFOTO", length = 512, nullable = false)
	private String pathFoto;

	@Column(name = "DESCRICAO", length = 512)
	private String description;

	@Column(name = "ANOLANCAMENTO")
	private Date releaseYear;

	@NotNull
	@Column(name = "STATUSLIVRO", length = 1, nullable = false)
	private String bookStatus;

	@Column(name="DONOLIVRO", nullable = true)
	private Long bookOwner;

	@Column(name="GENERO", length = 1, nullable = true)
	private String genre;

	@Column(name="TIPOCOMPARTILHAMENtO", length = 1)
	private String sharingType;
	
	public BookEntity() {
	}

	public BookEntity(String name, String author, String publishingCompany, String pathFoto, String description,
			Date releaseYear, String bookStatus, Long bookOwner) { 
		super();
		this.name = name;
		this.author = author;
		this.publishingCompany = publishingCompany;
		this.pathFoto = pathFoto;
		this.description = description;
		this.releaseYear = releaseYear;
		this.bookStatus = bookStatus;
		this.bookOwner = bookOwner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishingCompany() {
		return publishingCompany;
	}

	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Long getBookOwner() {
		return bookOwner;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setBookOwner(Long bookOwner) {
		this.bookOwner = bookOwner;
	}

	public String getSharingType() {
		return sharingType;
	}

	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
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