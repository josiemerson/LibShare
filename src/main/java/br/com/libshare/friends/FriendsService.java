package br.com.libshare.friends;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.FRIENDS_PATH)
public class FriendsService extends GenericService<FriendsEntity,Long> {

	@PersistenceContext
	private EntityManager em;
	
	@RequestMapping(method = RequestMethod.GET, value = "/statusFriend/{codUsu}/{codUsuFriend}")
	public ResponseEntity<?> getFriendPerId( @PathVariable("codUsu") Long codUsu, @PathVariable("codUsuFriend") Long codUsuFriend) {
		ResponseEntity<?> response = null;
		Query findProfiles = em.createNativeQuery("SELECT A.* FROM AMIGOS A WHERE A.MEUCODUSU = :CODUSU AND A.CODUSUAMIGO = :CODUSUAMIGO", FriendsEntity.class);
		findProfiles.setParameter("CODUSU", codUsu);
		findProfiles.setParameter("CODUSUAMIGO", codUsuFriend);

		try {
			FriendsEntity friends = (FriendsEntity) findProfiles.getSingleResult();			
			if (friends != null) {
				response = new ResponseEntity<FriendsEntity>(friends, HttpStatus.OK);
			}
		}catch (NoResultException e) {
			response = new ResponseEntity<String>("", HttpStatus.OK);			
		}

		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/newFriend/")
	public ResponseEntity<?> newFriends(@RequestBody FriendsEntity friendsEntity) throws Exception {
		ResponseEntity<?> response = null;
//		UserEntity user = ((FriendsEntity) this.genericRepository).findByEmail(friendsEntity.getEmail());

		if(friendsEntity != null) {
			super.insert(friendsEntity);
		}

		response = new ResponseEntity<FriendsEntity>(friendsEntity, HttpStatus.OK);			

		return response;
	}
}