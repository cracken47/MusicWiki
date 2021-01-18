package com.karan.musicwiki.vo;

public class Resource<T> {
    private Status STATUS;
    private T data;
    private String message;
    private int errCode;
    private ErrorType ERR_TYPE;

    public Resource(){}

    private Resource(Status STATUS, T data, String message) {
        this.STATUS = STATUS;
        this.data = data;
        this.message = message;
    }

    public Resource(Status STATUS, String message, int errCode, ErrorType ERR_TYPE) {
        this.STATUS = STATUS;
        this.message = message;
        this.errCode = errCode;
        this.ERR_TYPE = ERR_TYPE;
    }

    public Resource success(T data) {
        return new Resource(Status.SUCCESS, data, null);
    }

    public Resource error(String msg, ErrorType errorType, int errCode) {
        return new Resource(Status.ERROR, msg, errCode, errorType);
    }

    public Resource loading(T data) {
        return new Resource(Status.LOADING, data, null);
    }

    public Status getSTATUS() {
        return STATUS;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }
}
