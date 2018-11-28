package br.com.libshare.utils.bean;

import java.util.Date;

public class SharingItemRequest {
	private Long 	sharing;
	private String 	sharingType;
	private Date 	devolutionDate;
	private Float 	sharingItemValue;
	private Long 	book;
	private String 	observation;
	private String 	statusSharing;
	private Long	sharingItem;

	public SharingItemRequest() {
	}

	public SharingItemRequest(Long sharing, String sharingType, Date devolutionDate, Float sharingItemValue,
			Long book, String observation, String statusSharing, Long sharingItem) {
		super();
		this.sharing = sharing;
		this.sharingType = sharingType;
		this.devolutionDate = devolutionDate;
		this.sharingItemValue = sharingItemValue;
		this.book = book;
		this.observation = observation;
		this.statusSharing = statusSharing;
		this.sharingItem = sharingItem;
	}

	public Long getSharing() {
		return sharing;
	}

	public void setSharing(Long sharing) {
		this.sharing = sharing;
	}

	public String getSharingType() {
		return sharingType;
	}

	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}

	public Date getDevolutionDate() {
		return devolutionDate;
	}

	public void setDevolutionDate(Date devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public Float getSharingItemValue() {
		return sharingItemValue;
	}

	public void setSharingItemValue(Float sharingItemValue) {
		this.sharingItemValue = sharingItemValue;
	}

	public Long getBook() {
		return book;
	}
	
	public void setBook(Long book) {
		this.book = book;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getStatusSharing() {
		return statusSharing;
	}

	public void setStatusSharing(String statusSharing) {
		this.statusSharing = statusSharing;
	}

	public Long getSharingItem() {
		return sharingItem;
	}

	public void setSharingItem(Long sharingItem) {
		this.sharingItem = sharingItem;
	}
}
