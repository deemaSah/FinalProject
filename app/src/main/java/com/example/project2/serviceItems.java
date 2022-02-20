package com.example.project2;

public class serviceItems {
    private String email;
    private String service;

    public serviceItems(String email, String service) {
        this.email = email;
        this.service = service;
    }

    public String getEmail() {
        return email;
    }

    public String getService() {
        return service;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setService(String service) {
        this.service = service;
    }
}
