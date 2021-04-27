package com.fiber.common.exception;

/**
 * @author panyox
 */
public class FiberException extends RuntimeException {

    private static final long serialVersionUID = 20023849284059L;

    public FiberException(final Throwable e) {
        super(e);
    }

    public FiberException(final String message) {
        super(message);
    }

    public FiberException(final String message, Throwable e) {
        super(message, e);
    }
}
