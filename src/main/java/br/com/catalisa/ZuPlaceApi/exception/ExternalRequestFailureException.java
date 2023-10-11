package br.com.catalisa.ZuPlaceApi.exception;

public class ExternalRequestFailureException extends InterruptedException{

    public ExternalRequestFailureException(String msg) {
        super("Request externo falhou: " + msg);
    }
}
