package br.com.libshare.utils.msg;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpMessage {

	public static ResponseEntity<?> showError(String msg) {
		return showError(msg, HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> showError(String msg, HttpStatus code) {
		return buildMsgReturn("error", msg, code);
	}

	public static ResponseEntity<?> showAlert(String msg) {
		return showAlert(msg, HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> showAlert(String msg, HttpStatus code) {
		return buildMsgReturn("warn", msg, code);
	}

	public static ResponseEntity<?> showSucess(String msg) {
		return showSucess(msg, HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> showSucess(String msg, HttpStatus code) {
		return buildMsgReturn("sucess", msg, code);
	}

	public static ResponseEntity<?> showInfo(String msg) {
		return showInfo(msg, HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> showInfo(String msg, HttpStatus code) {
		return buildMsgReturn("info", msg, code);
	}

	private static ResponseEntity<?> buildMsgReturn(String type, String msg, HttpStatus code) {
		ResponseEntity<?> response;
		Map errorMap = new HashMap<>();
		errorMap.put("typeMsg", type);
		errorMap.put("msg", msg);
		response = new ResponseEntity<>(errorMap, code);
		return response;
	}
}
