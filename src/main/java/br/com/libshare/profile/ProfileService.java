package br.com.libshare.profile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aspectj.bridge.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.libshare.user.UserEntity;
import br.com.libshare.user.UserRepository;
import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.PROFILE_PATH)
public class ProfileService extends GenericService<ProfileEntity, Long> {

	@PersistenceContext
	private EntityManager em;

	@RequestMapping(method = RequestMethod.GET, value="/book")
	public String getUserPerGenreBook1() {
		return new String("Passou");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/profileByCodUsu/{codUsu}")
	public ResponseEntity<?> getByPerId( @PathVariable("codUsu") Long codUsu) {
		ResponseEntity<?> response = null;
		try {
			ProfileEntity profile = ((ProfileRepository)this.genericRepository).findByCodUsu(codUsu);	
			if(profile == null || profile.getActive() == null) {
				response = this.showAlert("É necessário finalizar o cadastro para continuar usufruindo do nosso serviço.", HttpStatus.FOUND);				
			} else {
				
				response = new ResponseEntity<ProfileEntity>(profile, HttpStatus.OK);
			}
		}catch (EntityNotFoundException enfe) {
			response = this.showAlert("É necessário finalizar o cadastro para continuar usufruindo do nosso serviço.", HttpStatus.FOUND);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/bookgenre/{genre}")
	public ResponseEntity<List<ProfileEntity>> getUserPerGenreBook(@PathVariable("genre") String genre) {
		ResponseEntity<List<ProfileEntity>> response = null;
		Query findProfiles = em.createNativeQuery("SELECT P.* FROM PERFIL P INNER JOIN LIVRO L ON (P.CODUSU = L.DONOLIVRO) WHERE L.GENERO = :GENERO AND P.ATIVO = 'S'", ProfileEntity.class);
		findProfiles.setParameter("GENERO", genre);
		List<ProfileEntity> profiles = findProfiles.getResultList();

		if (profiles.isEmpty()) {
			Query findProfilesAll = em.createNativeQuery("SELECT P.* FROM PERFIL P WHERE P.ATIVO = 'S'", ProfileEntity.class);
			List<ProfileEntity> profilesAll = findProfilesAll.getResultList();

			response = new ResponseEntity<List<ProfileEntity>>(profilesAll, HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<List<ProfileEntity>>(profiles, HttpStatus.OK);
		}

		return response;
	}
}