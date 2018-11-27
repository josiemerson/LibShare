package br.com.libshare.profile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aspectj.bridge.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.libshare.user.UserEntity;
import br.com.libshare.user.UserRepository;
import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;
import br.com.libshare.utils.msg.HttpMessage;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/bookgenre/{genre}/{codUsu}")
	public ResponseEntity<List<ProfileEntity>> getUserPerGenreBookWithoutUserLogged(@PathVariable("genre") String genre, @PathVariable("codUsu") Long codUsuLogged) {
		return getProfiles(genre, codUsuLogged);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/bookgenre/{genre}")
	public ResponseEntity<List<ProfileEntity>> getUserPerGenreBook(@PathVariable("genre") String genre) {
		return getProfiles(genre, null);
	}
	
	private ResponseEntity<List<ProfileEntity>> getProfiles(String genre, Long codUsuLogged){
		ResponseEntity<List<ProfileEntity>> response = null;
		StringBuilder sbQuery = new StringBuilder("SELECT DISTINCT P.* FROM PERFIL P INNER JOIN LIVRO L ON (P.CODUSU = L.DONOLIVRO) WHERE L.GENERO = :GENERO AND L.STATUSLIVRO NOT IN ('V', 'P') AND P.ATIVO = 'S'");
		if (codUsuLogged != null) {
			sbQuery.append(" AND P.CODUSU <> :CODUSULOGGED ");
		}

		Query findProfiles = em.createNativeQuery(sbQuery.toString(), ProfileEntity.class);
		findProfiles.setParameter("GENERO", genre);
		if (codUsuLogged != null) {
			findProfiles.setParameter("CODUSULOGGED", codUsuLogged);
		}

		List<ProfileEntity> profiles = findProfiles.getResultList();

		if (profiles.isEmpty()) {
//			StringBuilder queryAll = new StringBuilder("SELECT P.* FROM PERFIL P WHERE P.ATIVO = 'S'");
//			if (codUsuLogged != null) {
//				queryAll.append(" AND P.CODUSU <> :CODUSULOGGED LIMIT 10 ");
//			}
//			Query findProfilesAll = em.createNativeQuery(queryAll.toString(), ProfileEntity.class);
//			if (codUsuLogged != null) {
//				findProfilesAll.setParameter("CODUSULOGGED", codUsuLogged);
//			}
//
//			List<ProfileEntity> profilesAll = findProfilesAll.getResultList();
//
//			
//			response = new ResponseEntity<List<ProfileEntity>>(profilesAll, HttpStatus.FOUND);
			
			response = new ResponseEntity<List<ProfileEntity>>(profiles, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<ProfileEntity>>(profiles, HttpStatus.OK);
		}

		return response;
	}
}