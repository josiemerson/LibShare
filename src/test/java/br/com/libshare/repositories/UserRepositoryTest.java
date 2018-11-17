package br.com.libshare.repositories;

/*
 * Josiemerson.Lacerda
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.libshare.user.UserEntity;
import br.com.libshare.user.UserRepository;
import br.com.libshare.utils.AbstractTest;

public class UserRepositoryTest extends AbstractTest {

	private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);

	@Autowired
	private UserRepository userRepository;

	@Test
	public void findAllTest() {
		List<UserEntity> users = this.userRepository.findAll();

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Test FindAll(): " + users);
		}
	}

	@Test
	public void findPerEmail() {
		UserEntity user = this.userRepository.findByEmail("josiemersonsouzalacerda@gmail.com");

		if(user == null) {
			LOGGER.error("Usuário ou senha não cadastrados.");
		} else if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Test Find(): " + user);
		}
	}

	@Test
	public void findUserPerId() {
//		UserEntity user = this.userRepository.getOne(1L);
//
//		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("Test FindUserPerId(): " + user);
//		}
	}

}
