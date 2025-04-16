package com.niveus.student.exception;

import java.io.Serial;

public class DataNotFoundException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {
		super(message);
	}
}
