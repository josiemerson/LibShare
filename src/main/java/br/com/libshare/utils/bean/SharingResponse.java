package br.com.libshare.utils.bean;

import br.com.libshare.sharing.SharingEntity;
import br.com.libshare.sharingItem.SharingItemEntity;

public class SharingResponse {
	private SharingEntity sharingEntity;
	private SharingItemEntity sharingItemEntity;

	public SharingResponse(SharingEntity sharingEntity, SharingItemEntity sharingItemEntity) {
		this.sharingEntity = sharingEntity;
		this.sharingItemEntity = sharingItemEntity;
	}

	public SharingEntity getSharingEntity() {
		return sharingEntity;
	}

	public void setSharingEntity(SharingEntity sharingEntity) {
		this.sharingEntity = sharingEntity;
	}

	public SharingItemEntity getSharingItemEntity() {
		return sharingItemEntity;
	}

	public void setSharingItemEntity(SharingItemEntity sharingItemEntity) {
		this.sharingItemEntity = sharingItemEntity;
	}
}