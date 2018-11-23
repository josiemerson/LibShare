package br.com.libshare.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.libshare.exception.ServerException;
import br.com.libshare.profile.ProfileEntity;
import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.BOOK_CASE_PATH)
public class BookService extends GenericService<BookEntity, Long> {
	private final Logger LOGGER = Logger.getLogger(this.getClass());

	private static final String ALL_GENRE = "T";
	@PersistenceContext
	private EntityManager em;

	@Override
	public BookEntity insert(@RequestBody BookEntity book) throws Exception {
		return super.insert(book);
	}

	@Override
	public void update(@RequestBody BookEntity book) {
		super.update(book);
	}

	@Override
	public BookEntity getOne(@PathVariable("id")Long id) {
		BookEntity book = super.getOne(id); 
		return book;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/bookbyuser/{user}/{genre}")
	public ResponseEntity<List<BookEntity>> getUserPerGenreBook(@PathVariable("user") String user, @PathVariable("genre") String genre) {
		ResponseEntity<List<BookEntity>> response = null;
		Query findBooks = em.createNativeQuery("SELECT L.* FROM LIVRO L WHERE "
				+ "(:GENERO = '" + ALL_GENRE + "' OR L.GENERO = :GENERO) AND L.DONOLIVRO = :CODUSU AND L.STATUSLIVRO <> 'V'", BookEntity.class);
		//COALESCE no mysql é o mesmo que NVL do oracle

		findBooks.setParameter("GENERO", genre);
		findBooks.setParameter("CODUSU", user);
		List<BookEntity> books = findBooks.getResultList();

		if (books.isEmpty()) {
			Query findTopDezBooks = em.createNativeQuery("SELECT L.* FROM LIVRO L WHERE "
					+ "L.DONOLIVRO = :CODUSU LIMIT 10", BookEntity.class);
			findTopDezBooks.setParameter("CODUSU", user);
			List<BookEntity> booksTop10 = findTopDezBooks.getResultList();

			response = new ResponseEntity<List<BookEntity>>(booksTop10, HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<List<BookEntity>>(books, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/mybooks/{user}/{genre}")
	public ResponseEntity<List<?>> getAllBooksUser(@PathVariable("user") String user, @PathVariable("genre") String genre) {
		ResponseEntity<List<?>> response = null;
		Query findBooks = em.createNativeQuery("SELECT L.* FROM LIVRO L WHERE "
				+ "(:GENERO = '" + ALL_GENRE + "' OR L.GENERO = :GENERO) AND L.DONOLIVRO = :CODUSU AND L.STATUSLIVRO <> 'V'", BookEntity.class);
		//COALESCE no mysql é o mesmo que NVL do oracle
		
		findBooks.setParameter("GENERO", genre);
		findBooks.setParameter("CODUSU", user);
		List<?> books = findBooks.getResultList();
		
		if (books.isEmpty()) {
			this.showAlert("Você não possui livros cadastrados.");
		} else {
			response = new ResponseEntity<List<?>>(books, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value="/bookNew")
	public BookEntity insertNewBook(@RequestBody BookEntity entityObject) {
		this.LOGGER.debug(String.format("Request insert new record [%s].", entityObject));

		return super.genericRepository.save(entityObject);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/bookEdit")
	public void updateBook(@RequestBody BookEntity entityObject) {
		this.LOGGER.debug(String.format("Request to update the record [%s].", entityObject));

		if (entityObject.getId() == null) {
			String errorMessage = String.format("ID da entidade [%s] está nulo.", entityObject.getClass());
			this.LOGGER.error(errorMessage);
			throw new ServerException(errorMessage);
		}

		this.genericRepository.save(entityObject);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/bookDelete")
	public void delete(@RequestBody BookEntity entityObject) {
		this.LOGGER.debug(String.format("Request to delete the record [%s].", entityObject));

		this.genericRepository.delete(entityObject);
	}
}