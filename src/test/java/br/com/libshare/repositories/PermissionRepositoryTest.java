package br.com.libshare.repositories;

/*
 * Autor: Josiemerson.Lacerda
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.libshare.permission.PermissionEntity;
import br.com.libshare.permission.PermissionRepository;
import br.com.libshare.utils.AbstractTest;

public class PermissionRepositoryTest extends AbstractTest {

	private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);

	@Autowired
	private PermissionRepository permissionRepository;

	@Test
	public void findAllTest() {
		List<PermissionEntity> permissions = this.permissionRepository.findAll();

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Test FindAll(): " + permissions);
		}
	}

}
