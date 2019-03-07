package com.example.demo.basedata.user;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String password;
    private String role;
}
