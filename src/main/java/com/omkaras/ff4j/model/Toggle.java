package com.omkaras.ff4j.model;

public class Toggle {

    private String name;
    private boolean value;

    public Toggle(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public boolean isValue() {
        return value;
    }
}
