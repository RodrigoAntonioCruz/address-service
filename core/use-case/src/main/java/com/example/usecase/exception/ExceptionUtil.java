package com.example.usecase.exception;

public class ExceptionUtil {

	private ExceptionUtil() {
	}

	public static void throwExceptionIf(boolean condition, RuntimeException exception) {

		if (condition) {
			throw exception;
		}
	}
}
