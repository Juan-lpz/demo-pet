package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class PetException extends Exception{

    private static final long serialVersionUID = -4372761125869221102L;
    private final HttpStatus statusHttp;
    private final String mensaje;
    private final String code;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public HttpStatus getStatusHttp() {
        return statusHttp;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getCode() {
        return code;
    }

    public PetException(HttpStatus statusHttp, String mensaje, String code) {
        super();
        this.statusHttp = statusHttp;
        this.mensaje = mensaje;
        this.code = code;
    }
}
