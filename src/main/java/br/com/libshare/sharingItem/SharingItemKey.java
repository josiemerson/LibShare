package br.com.libshare.sharingItem;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.libshare.utils.BaseKey;

@Embeddable
public class SharingItemKey extends BaseKey {

	private static final long serialVersionUID = 201602010536L;

	@Column(name = "CODCOMP", length = 11, nullable = false)
	private Long sharing;

	@Column(name = "CODITEMCOMP", length = 11, nullable = false)
	private Long sharingItem;

	public SharingItemKey() {
	}

	public SharingItemKey(Long sharing, Long sharingItem) {
		super();
		this.sharing = sharing;
		this.sharingItem = sharingItem;
	}

	public Long getSharing() {
		return sharing;
	}

	public void setSharing(Long sharing) {
		this.sharing = sharing;
	}

	public Long getSharingItem() {
		return sharingItem;
	}

	public void setSharingItem(Long sharingItem) {
		this.sharingItem = sharingItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sharing == null) ? 0 : sharing.hashCode());
		result = prime * result + ((sharingItem == null) ? 0 : sharingItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SharingItemKey other = (SharingItemKey) obj;
		if (sharing == null) {
			if (other.sharing != null)
				return false;
		} else if (!sharing.equals(other.sharing))
			return false;
		if (sharingItem == null) {
			if (other.sharingItem != null)
				return false;
		} else if (!sharingItem.equals(other.sharingItem))
			return false;
		return true;
	}
}