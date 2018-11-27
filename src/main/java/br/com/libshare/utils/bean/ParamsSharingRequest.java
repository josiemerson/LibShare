package br.com.libshare.utils.bean;

import java.time.LocalDate;
import java.util.Date;

public class ParamsSharingRequest {
	private Long 		codUsu;
	private Long 		codUsuLogged;
	private Date 		dtCompIni;
	private Date 		dtCompFim;
	private Date 		dtDevIni;
	private Date 		dtDevFim;
	private String 		statusBook;
	private String		sharingWithMe;
	
	public ParamsSharingRequest() {
	}
	
	public ParamsSharingRequest(Long codUsu, Long codUsuLogged, Date dtCompIni, Date dtCompFim, Date dtDevIni,
			Date dtDevFim, String statusBook, String sharingWithMe) {
		super();
		this.codUsu = codUsu;
		this.codUsuLogged = codUsuLogged;
		this.dtCompIni = dtCompIni;
		this.dtCompFim = dtCompFim;
		this.dtDevIni = dtDevIni;
		this.dtDevFim = dtDevFim;
		this.statusBook = statusBook;
		this.sharingWithMe = sharingWithMe;
	}
	
	public Long getCodUsu() {
		return codUsu;
	}
	public void setCodUsu(Long codUsu) {
		this.codUsu = codUsu;
	}
	public Long getCodUsuLogged() {
		return codUsuLogged;
	}

	public void setCodUsuLogged(Long codUsuLogged) {
		this.codUsuLogged = codUsuLogged;
	}

	public Date getDtCompIni() {
		return dtCompIni;
	}
	public void setDtCompIni(Date dtCompIni) {
		this.dtCompIni = dtCompIni;
	}
	public Date getDtCompFim() {
		return dtCompFim;
	}
	public void setDtCompFim(Date dtCompFim) {
		this.dtCompFim = dtCompFim;
	}
	public Date getDtDevIni() {
		return dtDevIni;
	}
	public void setDtDevIni(Date dtDevIni) {
		this.dtDevIni = dtDevIni;
	}
	public Date getDtDevFim() {
		return dtDevFim;
	}
	public void setDtDevFim(Date dtDevFim) {
		this.dtDevFim = dtDevFim;
	}
	public String getStatusBook() {
		return statusBook;
	}
	public void setStatusBook(String statusBook) {
		this.statusBook = statusBook;
	}

	public String getSharingWithMe() {
		return sharingWithMe;
	}

	public void setSharingWithMe(String sharingWithMe) {
		this.sharingWithMe = sharingWithMe;
	}
}