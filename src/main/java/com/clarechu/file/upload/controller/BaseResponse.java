package com.clarechu.file.upload.controller;

public class BaseResponse<T> {
    private int code;

    private String message;

    private T data;

    public static class Builder<T> {
        private int code;

        private String message;

        private T data;

        public Builder success() {
            code = 200;
            message = "success";
            return this;
        }

        public Builder fail() {
            code = 500;
            message = "fail";
            return this;
        }

        public BaseResponse<T> builder() {
            return new BaseResponse<>(this.code, this.message, data);
        }
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
