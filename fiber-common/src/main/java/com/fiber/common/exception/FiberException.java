package com.fiber.common.exception;

/**
 * @author panyox
 */
public class FiberException extends RuntimeException {

    private static final long serialVersionUID = 20023849284059L;

    private int code;

    public FiberException(int code, final String message) {
        super(message);
        this.code = code;
    }

    public FiberException(final Throwable e) {
        super(e);
    }

    public FiberException(final String message) {
        super(message);
    }

    public FiberException(final String message, Throwable e) {
        super(message, e);
    }

    public int getCode() {
        return code;
    }
}
