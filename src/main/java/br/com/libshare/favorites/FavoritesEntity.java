package br.com.libshare.favorites;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.libshare.book.BookEntity;
import br.com.libshare.user.UserEntity;
import br.com.libshare.utils.BaseEntity;

@Entity
@Table(name = "FAVORITOS")
@AttributeOverride(name = "id", column = @Column(name = "CODFAVORITO"))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FavoritesEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201602010251L;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODLIVRO")
	private BookEntity book;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODUSU")
	private UserEntity user;

	public FavoritesEntity(BookEntity book, UserEntity user) {
		super();
		this.book = book;
		this.user = user;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}