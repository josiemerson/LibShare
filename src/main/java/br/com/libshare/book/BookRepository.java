package br.com.libshare.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

	public BookEntity findByBookOwner(Long codUsu);

	public BookEntity findByName(String name);

	public BookEntity findByAuthor(String author);

	public BookEntity findByPublishingCompany(String publishingCompany);

	public BookEntity findByBookOwnerAndBookStatus(Long codUsu, String status);
}
