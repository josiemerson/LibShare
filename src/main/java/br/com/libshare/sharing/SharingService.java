package br.com.libshare.sharing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;
import ch.qos.logback.classic.util.StatusListenerConfigHelper;

@RestController
@RequestMapping(path = ServicePath.SHARING_PORTAL_PATH)
public class SharingService extends GenericService<SharingEntity, Long> {

	@Override
	public SharingEntity insert(@RequestBody SharingEntity sharing) throws Exception {
		return super.insert(sharing);
	}

	@RequestMapping(method = RequestMethod.POST, value="/newSharing")
	public ResponseEntity<JsonObject> insertSharing(@RequestBody JsonObject sharing) throws Exception {
		
		
		return new ResponseEntity<JsonObject>(sharing, HttpStatus.OK);
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