package com.fdu.dandajun.ddj_backend.controller.request;

/**
 * @author Yu Zhexuan
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String name;

    public RegisterRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
