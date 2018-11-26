package br.com.libshare.utils.bean;

import java.time.LocalDate;

public class ParamsSharingRequest {
	private Long 		codUsu;
	private Long 		codUsuLogged;
	private LocalDate 	dtCompIni;
	private LocalDate 	dtCompFim;
	private LocalDate 	dtDevIni;
	private LocalDate 	dtDevFim;
	private String 		statusBook;
	private String		sharingWithMe;
	
	public ParamsSharingRequest() {
	}
	
	public ParamsSharingRequest(Long codUsu, Long codUsuLogged, LocalDate dtCompIni, LocalDate dtCompFim, LocalDate dtDevIni,
			LocalDate dtDevFim, String statusBook, String sharingWithMe) {
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

	public LocalDate getDtCompIni() {
		return dtCompIni;
	}
	public void setDtCompIni(LocalDate dtCompIni) {
		this.dtCompIni = dtCompIni;
	}
	public LocalDate getDtCompFim() {
		return dtCompFim;
	}
	public void setDtCompFim(LocalDate dtCompFim) {
		this.dtCompFim = dtCompFim;
	}
	public LocalDate getDtDevIni() {
		return dtDevIni;
	}
	public void setDtDevIni(LocalDate dtDevIni) {
		this.dtDevIni = dtDevIni;
	}
	public LocalDate getDtDevFim() {
		return dtDevFim;
	}
	public void setDtDevFim(LocalDate dtDevFim) {
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