package br.com.alura.leilao.exceptions;

public class LimiteLancesUsuarioException extends RuntimeException{
    public LimiteLancesUsuarioException(String message) {
        super(message);
    }
}
