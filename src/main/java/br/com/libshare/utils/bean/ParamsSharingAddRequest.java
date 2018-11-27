package br.com.libshare.utils.bean;

import java.util.Date;
import java.util.List;

public class ParamsSharingAddRequest {
	private Long 		userOrigin;
	private Long 		userDestiny;
	private Date 		sharingDateAndHour;
	private Float 		sharingValue;
	private List<SharingItemRequest> sharingItens;

	public ParamsSharingAddRequest() {
	}
	
	public ParamsSharingAddRequest(Long userOrigin, Long userDestiny, Date sharingDateAndHour, Float sharingValue, List<SharingItemRequest> sharingItens) {
		super();
		this.userOrigin = userOrigin;
		this.userDestiny = userDestiny;
		this.sharingDateAndHour = sharingDateAndHour;
		this.sharingItens = sharingItens;
	}

	public Long getUserOrigin() {
		return userOrigin;
	}

	public void setUserOrigin(Long userOrigin) {
		this.userOrigin = userOrigin;
	}

	public Long getUserDestiny() {
		return userDestiny;
	}

	public void setUserDestiny(Long userDestiny) {
		this.userDestiny = userDestiny;
	}

	public Date getSharingDateAndHour() {
		return sharingDateAndHour;
	}

	public void setSharingDateAndHour(Date sharingDateAndHour) {
		this.sharingDateAndHour = sharingDateAndHour;
	}

	public Float getSharingValue() {
		return sharingValue;
	}

	public void setSharingValue(Float sharingValue) {
		this.sharingValue = sharingValue;
	}

	public List<SharingItemRequest> getSharingItens() {
		return sharingItens;
	}

	public void setSharingItens(List<SharingItemRequest> sharingItens) {
		this.sharingItens = sharingItens;
	}
	

}