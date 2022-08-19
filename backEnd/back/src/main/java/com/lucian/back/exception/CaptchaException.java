package com.lucian.back.exception;

/**
 * 验证码异常
 * @author denglingxiang
 * @date 2022/08/18 09:59
 **/
public class CaptchaException extends Exception {
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CaptchaException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CaptchaException(String message) {
        super(message);
    }
}
