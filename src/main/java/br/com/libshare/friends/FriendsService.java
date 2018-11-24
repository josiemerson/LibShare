package br.com.libshare.friends;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.libshare.profile.ProfileEntity;
import br.com.libshare.user.UserEntity;
import br.com.libshare.user.UserRepository;
import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping(path = ServicePath.FRIENDS_PATH)
public class FriendsService extends GenericService<FriendsEntity,Long> {

	@PersistenceContext
	private EntityManager em;

	@RequestMapping(method = RequestMethod.GET, value = "/statusFriend/{codUsu}/{codUsuFriend}")
	public ResponseEntity<?> getFriendPerId( @PathVariable("codUsu") Long codUsu, @PathVariable("codUsuFriend") Long codUsuFriend) {
		ResponseEntity<?> response = null;
		Query findProfiles = em.createNativeQuery("SELECT A.* FROM AMIGOS A WHERE "
				+ "(A.MEUCODUSU = :CODUSU OR A.CODUSUAMIGO = :CODUSU)  AND (A.MEUCODUSU = :CODUSUAMIGO OR A.CODUSUAMIGO = :CODUSUAMIGO) ", FriendsEntity.class);
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

	@RequestMapping(method = RequestMethod.GET, value="/listFriend/{codUsu}/{status}")
	public ResponseEntity<?> newFriends(@PathVariable("codUsu") Long codUsu,@PathVariable("status") String status) throws Exception {
		ResponseEntity<?> response = null;
		Query findProfiles = em.createNativeQuery("SELECT A.* FROM AMIGOS A WHERE (A.MEUCODUSU = :CODUSU OR A.CODUSUAMIGO = :CODUSU) AND (:STATUS = 'A' OR A.STATUS = :STATUS)", FriendsEntity.class);
		findProfiles.setParameter("CODUSU", codUsu);
		findProfiles.setParameter("STATUS", status);

		List<FriendsResponse> friendsResponse = null;
		try {
			List<FriendsEntity> friends = findProfiles.getResultList();
			if (friends != null) {
				friendsResponse = new ArrayList<FriendsResponse>();

				for(FriendsEntity friend : friends) {

					Long codUserFriend = friend.getMyUserCode();
					if (codUserFriend == codUsu) {
						codUserFriend =  friend.getUserCodeFriend();
					}

					UserEntity userFriend = getUserById(codUserFriend);
					if (userFriend != null) {
						FriendsResponse responseFriends = new FriendsResponse(friend, userFriend);
						friendsResponse.add(responseFriends);						
					}
				}
				
				response = new ResponseEntity<>(friendsResponse, HttpStatus.OK);
			} else {
				response = this.showAlert("Você não possui livros cadastrados.");
			}
		}catch (NoResultException e) {
			response = new ResponseEntity<String>("", HttpStatus.OK);			
		}

		return response;
	}

	private UserEntity getUserById(Long id) {
		UserEntity user = null; 
		if (id != null) {
			Query findUser = em.createNativeQuery("SELECT A.* FROM USUARIO A INNER JOIN PERFIL P ON (A.CODUSU = P.CODUSU)  WHERE A.CODUSU = :CODUSU AND P.ATIVO = 'S'", UserEntity.class);
			findUser.setParameter("CODUSU", id);

			try {
				user = (UserEntity) findUser.getSingleResult();

				Query findProfiles = em.createNativeQuery("SELECT P.* FROM PERFIL P WHERE P.CODUSU = :CODUSU", ProfileEntity.class);
				findProfiles.setParameter("CODUSU", id);

				try {
					ProfileEntity profile = (ProfileEntity) findProfiles.getSingleResult();
					user.setProfile(profile);
				}catch (NoResultException ignore) {
				}
			}catch (NoResultException ignore) {
			}
		}

		return user;
	}
}