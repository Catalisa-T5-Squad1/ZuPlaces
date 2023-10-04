package br.com.catalisa.ZuPlaceApi.exception;

public class SpaceNotFound extends RuntimeException{
    public SpaceNotFound(Long id) {
        super("Espaço não encontrado com o ID: " + id);
    }
}
