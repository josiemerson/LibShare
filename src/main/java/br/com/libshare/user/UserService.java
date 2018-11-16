package br.com.libshare.user;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import br.com.libshare.permission.PermissionEntity;
import br.com.libshare.profile.ProfileEntity;
import br.com.libshare.profile.ProfileRepository;
import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;
import br.com.libshare.utils.image.DataImage;
import br.com.libshare.utils.image.ImageUtils;

@RestController
@RequestMapping(path = ServicePath.USER_PATH)
public class UserService extends GenericService<UserEntity, Long> {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProfileRepository profileRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserEntity insert(@RequestBody UserEntity user) throws Exception{
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		//verificar se a imagem está vindo com base64, se tiver converte se não deixa como está.
		user.getProfile().setPathFoto(treatmentImgGetName(user));

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
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		user.getProfile().setPathFoto(treatmentImgGetName(user));

		super.update(user);
	}

	@Override
	public UserEntity getOne(@PathVariable("id")Long id) {
		UserEntity user = super.getOne(id); 
		UserEntity user1 = ((UserRepository)super.genericRepository).findByEmail("josiemersonsouzalacerda@gmail.com");
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
			user = this.insert(userEntity);

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
}