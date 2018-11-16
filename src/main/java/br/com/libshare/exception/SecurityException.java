package br.com.libshare.exception;

import static br.com.libshare.exception.ExceptionConstants.SECURITY_EXCEPTION;

public class SecurityException extends GenericException {

	private static final long serialVersionUID = 201606062325L;

	public SecurityException() {
		super(SECURITY_EXCEPTION);
	}

}
