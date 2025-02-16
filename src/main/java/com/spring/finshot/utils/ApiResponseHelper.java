package com.spring.finshot.utils;

import java.util.List;

public class ApiResponseHelper<T> {
    private String status;
    private List<T> data;

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
