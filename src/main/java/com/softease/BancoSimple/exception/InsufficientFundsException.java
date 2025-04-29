package com.softease.BancoSimple.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(Integer cuentaId, String mensaje) {
        super("Cuenta " + cuentaId + ": " + mensaje);
    }
}
