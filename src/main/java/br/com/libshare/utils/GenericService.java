package br.com.libshare.utils;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.libshare.exception.ServerException;
import br.com.libshare.user.UserEntity;
import br.com.libshare.utils.msg.HttpMessage;

public abstract class GenericService<T extends BaseEntity<ID>, ID extends Serializable> extends HttpMessage implements ServiceMap {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	protected JpaRepository<T, ID> genericRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<T> findAll() {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug("Requesting all records.");
		}

		return this.genericRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public T getOne( @PathVariable("id") ID id) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Get user with id [%s].", id));
		}

		return this.genericRepository.getOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public T insert(@RequestBody T entityObject) throws Exception {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Saving the entity [%s].", entityObject));
		}

		return this.genericRepository.save(entityObject);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody T entityObject) {
		this.LOGGER.debug(String.format("Request to update the record [%s].", entityObject));

		if (entityObject.getId() == null) {
			String errorMessage = String.format("ID da entidade [%s] está nulo.", entityObject.getClass());
			this.LOGGER.error(errorMessage);
			throw new ServerException(errorMessage);
		}

		this.genericRepository.save(entityObject);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestBody T entityObject) {
		this.LOGGER.debug(String.format("Request to delete the record [%s].", entityObject));

		this.genericRepository.delete(entityObject);
	}

	public UserEntity getOne() {
		return null;
	}

}
