package com.br.vitorhernandes.servicoOnline.service.exception;

public abstract class BaseException extends RuntimeException {

	public BaseException(
			String msg) {
		super(msg);
	}

	public BaseException(
			String msg,
			Throwable cause) {
		super(msg, cause);
	}
}
