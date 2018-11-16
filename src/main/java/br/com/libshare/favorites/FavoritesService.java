package br.com.libshare.favorites;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.FAVORITES_PATH)
public class FavoritesService extends GenericService<FavoritesEntity, Long> {

	@Override
	public FavoritesEntity getOne(Long id) {
		FavoritesEntity favorites =  super.genericRepository.getOne(id); 
		return favorites;
	}
}