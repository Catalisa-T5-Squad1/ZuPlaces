package br.com.catalisa.ZuPlaceApi.exception;

public class ResourceNotFoud extends RuntimeException{
    public ResourceNotFoud(Long id) {
        super("Recurso não encontrado com o ID: " + id);
    }
}
