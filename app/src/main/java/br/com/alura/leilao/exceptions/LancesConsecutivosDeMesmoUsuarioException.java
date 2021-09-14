package br.com.alura.leilao.exceptions;

public class LancesConsecutivosDeMesmoUsuarioException extends RuntimeException{
    public LancesConsecutivosDeMesmoUsuarioException(String message) {
        super(message);
    }
}
