package com.example.model;

public enum Role {

    ADMIN("Admin"), USER("User");

    private String label;

    private Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
