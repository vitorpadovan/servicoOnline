package com.br.vitorhernandes.servicoOnline.service.exception;

public class DataIntegrityException extends BaseException {

	public DataIntegrityException(
			String msg) {
		super(msg);
	}

	public DataIntegrityException(
			String msg,
			Throwable cause) {
		super(msg, cause);
	}
}
