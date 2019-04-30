package com.example.baseproject.models;

public class APIResponse {
    private String status;
    private Object response;

    public APIResponse(Object response, String status){
        this.response=response;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }


    public Object getResponse() {
        return response;
    }

}
