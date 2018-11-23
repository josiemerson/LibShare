package br.com.libshare.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.libshare.permission.PermissionEntity;
import br.com.libshare.profile.ProfileEntity;
import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;
import br.com.libshare.utils.image.DataImage;
import br.com.libshare.utils.image.ImageUtils;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping(path = ServicePath.USER_PATH)
public class UserService extends GenericService<UserEntity, Long> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserEntity insert(@RequestBody UserEntity user) throws Exception{
		setPasswordEncode(user);

		//verificar se a imagem está vindo com base64, se tiver converte se não deixa como está.
		ProfileEntity profile = user.getProfile();
		if(profile != null) {
			profile.setPathFoto(treatmentImgGetName(user));
		}

		return super.insert(user);
	}

	@Override
	public void update(@RequestBody UserEntity user) {
		
		List<PermissionEntity> permissions = new ArrayList<PermissionEntity>();
		PermissionEntity permission = new PermissionEntity();
		permission.setId(new Long(2));
		permission.setRole("ROLE_USER");
		permissions.add(permission);

		user.setPermissions(permissions);

		UserEntity userDB = this.getOne(user.getId());
		if (!userDB.getPassword().equals(user.getPassword())) {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));			
		}

		ProfileEntity profile = user.getProfile();
		if(profile != null) {
			String img = treatmentImgGetName(user);
			if (img != null) {

				profile.setPathFoto(img);				
			} else {
				profile.setPathFoto(userDB.getProfile().getPathFoto());
			}
		}

		super.update(user);
	}

	@Override
	public UserEntity getOne(@PathVariable("id")Long id) {
		Query userResult = em.createNativeQuery("SELECT A.* FROM USUARIO A WHERE A.CODUSU = :CODUSU", UserEntity.class);
		userResult.setParameter("CODUSU", id);

		Query profileResult = em.createNativeQuery("SELECT A.* FROM PERFIL A WHERE A.CODUSU = :CODUSU AND A.ATIVO = 'S'", ProfileEntity.class);
		profileResult.setParameter("CODUSU", id);

		UserEntity user = null;
		try {
			user = (UserEntity) userResult.getSingleResult();
			ProfileEntity profile = (ProfileEntity) profileResult.getSingleResult();

			user.setProfile(profile);
		}catch (NoResultException e) {
		}

		return user;
	}

//	@RequestMapping(method = RequestMethod.GET, value = "/userByCategoryBook/{genre}")
//	public ResponseEntity<List<ProfileEntity>> getUserPerGenreBook(@PathVariable("genre") String genre) {
//		ResponseEntity<List<ProfileEntity>> response = null;
//		TypedQuery<ProfileEntity> findProfiles = em.createQuery("SELECT P FROM PERFIL P INNER JOIN LIVRO L ON (P.CODUSU = L.DONOLIVRO) WHERE GENERO = 'B' AND P.ATIVO = 'S'", ProfileEntity.class);
//		List<ProfileEntity> profiles = findProfiles.getResultList();
//
//		if (profiles.isEmpty()) {
//			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} else {
//			response = new ResponseEntity<List<ProfileEntity>>(profiles, HttpStatus.OK);
//		}
//
//		return response;
//	}

	@RequestMapping(method = RequestMethod.POST, value="/newUser")
	public ResponseEntity<?> newUser(@RequestBody UserEntity userEntity) throws Exception {
		ResponseEntity<?> response = null;
		UserEntity user = ((UserRepository) this.genericRepository).findByEmail(userEntity.getEmail());

		if (user != null) {

			String error = "E-mail já cadastrado na nossa base de dados.";
			response = this.showError(error);
		} else {
			if(userEntity != null) {
				userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
			}
//			setPasswordEncode(userEntity);

			user = super.insert(userEntity);

			response = new ResponseEntity<UserEntity>(user, HttpStatus.OK);			
		}

		return response;
	}

	private String treatmentImgGetName(UserEntity user) {
		String pathFoto = user.getProfile().getPathFoto();
		String[] tokenBase64 = null;
		String fileName = null;
		if (pathFoto != null && pathFoto.indexOf("base64") > -1) {
			fileName = pathFoto.split("_filename@", -1)[0];

			DataImage dataImage = new DataImage();
			dataImage.base64Image  = pathFoto.split("_filename@", -1)[1].split(",", -1)[1];
			dataImage.nameFile =  fileName;
			try {
				File file = ImageUtils.convertBase64ToFile(dataImage);				
			}catch (Exception e) {
			}
		}

		return fileName;
	}

	private void setPasswordEncode(UserEntity user) {
		if (user != null) {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));			
		}
	}
}