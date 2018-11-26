package br.com.libshare.sharing;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import br.com.libshare.friends.FriendsEntity;
import br.com.libshare.friends.FriendsResponse;
import br.com.libshare.sharingItem.SharingItemEntity;
import br.com.libshare.user.UserEntity;
import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;
import br.com.libshare.utils.StringUtils;
import br.com.libshare.utils.bean.ParamsSharingRequest;
import br.com.libshare.utils.bean.SharingResponse;
import ch.qos.logback.classic.util.StatusListenerConfigHelper;

@RestController
@RequestMapping(path = ServicePath.SHARING_PORTAL_PATH)
public class SharingService extends GenericService<SharingEntity, Long> {

	private static final String QUERY_DEFAULT = "SELECT {{TABELA}}.* FROM ITEMCOMPARTILHAMENTO I WHERE ";

	@PersistenceContext
	private EntityManager em;

	@Override
	public SharingEntity insert(@RequestBody SharingEntity sharing) throws Exception {
		return super.insert(sharing);
	}

	@RequestMapping(method = RequestMethod.POST, value="/getSharing/")
	public ResponseEntity<?> getSharing(@RequestBody ParamsSharingRequest params) throws Exception {
		ResponseEntity<?> response = null;

		Query findSharing = em.createNativeQuery(buildQueryFromParams(params, false), SharingEntity.class);

		List<SharingResponse> listSharing = null;
		try {
			List<SharingEntity> sharings = findSharing.getResultList();
			if (sharings != null) {
				listSharing = new ArrayList<SharingResponse>();

				for(SharingEntity sharing : sharings) {

					Long codComp = sharing.getId();

					List<SharingItemEntity> itens = getSharingItem(codComp);
					if (itens.size() > 0) {
						SharingResponse responseSharing = new SharingResponse(sharing, itens);
						listSharing.add(responseSharing);						
					}
				}
				
				response = new ResponseEntity<>(listSharing, HttpStatus.OK);
			} else {
				response = this.showAlert("Não foram encontrados resultados para a pesquisa.");
			}
		}catch (NoResultException e) {
			response = new ResponseEntity<String>("", HttpStatus.OK);			
		}

		return response;
	}
	
	private List<SharingItemEntity> getSharingItem(Long codSharing) {
		List<SharingItemEntity> itens = new ArrayList<SharingItemEntity>();
		try {
			Query findSharing = em.createNativeQuery("SELECT I.* FROM ITEMCOMPARTILHAMENTO WHERE CODCOMP = :CODCOMP",
					SharingItemEntity.class);
			findSharing.setParameter("CODCOMP", codSharing);

			itens = findSharing.getResultList();
		} catch (NoResultException ignore) {
		}

		return itens;
	}

	private String buildQueryFromParams(ParamsSharingRequest params, boolean isItem) {
		String query = QUERY_DEFAULT;
		query = query.replace("{{TABELA}}", isItem ? "I" : "C");

		StringBuilder sbQuery = new StringBuilder(query);

		Long codUsuLogged = params.getCodUsuLogged();
		Long codUsu = params.getCodUsu();
		//Ambos = A, C= Comigo (Solicitante), P = Por Mim(sou o emprestador)
		if ("A".equals(params.getSharingWithMe())) {
			sbQuery.append("(CODUSUDESTINO = " + codUsuLogged + " OR CODUSUORIGEM = " + codUsuLogged + ")");

			if (StringUtils.isNotEmpty(codUsu)) {
				
				sbQuery.append(" AND (CODUSUDESTINO = " + codUsu + " OR CODUSUORIGEM = " + codUsu + ")");
			}
		} else if ("C".equals(params.getSharingWithMe())) {
			sbQuery.append(" CODUSUDESTINO = " + codUsuLogged);

			if (StringUtils.isNotEmpty(codUsu)) {
				
				sbQuery.append("AND CODUSUORIGEM = " + codUsu);
			}			
		} if ("P".equals(params.getSharingWithMe())) {
			sbQuery.append(" CODUSUORIGEM = " + codUsuLogged);			

			if (StringUtils.isNotEmpty(codUsu)) {

				sbQuery.append(" AND CODUSUDESTINO = " + codUsu );
			}
		}

		if (StringUtils.isNotEmpty(params.getDtCompIni())) {
			sbQuery.append(" AND DHCOMP >= '" + params.getDtCompIni() + "'");
		}

		if (StringUtils.isNotEmpty(params.getDtCompFim())) {
			sbQuery.append(" AND DHCOMP <= '" + params.getDtCompFim() + "'");
		}

		if (StringUtils.isNotEmpty(params.getDtDevIni())) {
			sbQuery.append(" AND DTDEVOL >= '" + params.getDtDevIni() + "'");
		}

		if (StringUtils.isNotEmpty(params.getDtDevFim())) {
			sbQuery.append(" DTDEVOL <= '" + params.getDtDevFim() + "'");
		}

		if (StringUtils.isNotEmpty(params.getStatusBook())) {
			sbQuery.append(" AND STATUSCOMP = " + params.getStatusBook());
		}

		return sbQuery.toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/newSharing/")
	public ResponseEntity<?> insertSharing(@RequestBody SharingEntity sharing) throws Exception {
		
		super.insert(sharing);
		
		return new ResponseEntity<SharingEntity>(sharing, HttpStatus.OK);
	}

	@Override
	public void update(@RequestBody SharingEntity sharing) {
		super.update(sharing);
	}

	@Override
	public SharingEntity getOne(@PathVariable("id")Long id) {
		SharingEntity sharing = super.getOne(id); 
		return sharing;
	}
}