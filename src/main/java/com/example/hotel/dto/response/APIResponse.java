package com.example.hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class APIResponse<T> {
    @JsonProperty("status")
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private String message;

    public APIResponse() {
        status="success";
        message=null;
        data=null;
    }

    public APIResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setMessage(String message) {
        this.message = message;
    }
}
