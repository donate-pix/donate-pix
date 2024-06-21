package io.github.donatepix.mercadopago.authentication;

public class MercadoPagoAuthenticationException extends RuntimeException {
    public MercadoPagoAuthenticationException(String message) {
        super(message);
    }

    public MercadoPagoAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}