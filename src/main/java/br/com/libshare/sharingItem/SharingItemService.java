package br.com.libshare.sharingItem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.libshare.utils.GenericService;
import br.com.libshare.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.SHARING_ITEM_PORTAL_PATH)
public class SharingItemService extends GenericService<SharingItemEntity, SharingItemKey> {

}