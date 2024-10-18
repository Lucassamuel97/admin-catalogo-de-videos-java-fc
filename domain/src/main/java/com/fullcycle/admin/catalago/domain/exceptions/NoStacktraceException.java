package com.fullcycle.admin.catalago.domain.exceptions;

public class NoStacktraceException extends RuntimeException {
    public NoStacktraceException(final String message) {
        this(message, null);
    }
    // disabled writableStackTrace – whether or not the stack trace should be writable
    public NoStacktraceException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }
}
