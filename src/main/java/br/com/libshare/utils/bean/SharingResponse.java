package br.com.libshare.utils.bean;

import java.util.List;

import br.com.libshare.sharing.SharingEntity;
import br.com.libshare.sharingItem.SharingItemEntity;

public class SharingResponse {
	private final SharingEntity sharingEntity;
	private final List<SharingItemEntity> sharingItens;

	public SharingResponse(SharingEntity sharingEntity, List<SharingItemEntity> sharingItens) {
		this.sharingEntity = sharingEntity;
		this.sharingItens = sharingItens;
	}

	public SharingEntity getSharingEntity() {
		return sharingEntity;
	}

	public List<SharingItemEntity> getSharingItens() {
		return sharingItens;
	}

}