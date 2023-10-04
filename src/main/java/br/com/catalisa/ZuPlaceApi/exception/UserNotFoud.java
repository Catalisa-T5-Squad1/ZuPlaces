package br.com.catalisa.ZuPlaceApi.exception;

public class UserNotFoud extends RuntimeException{
    public UserNotFoud(Long id) {
        super("Usuário não encontrado com o ID: " + id);
    }
}
