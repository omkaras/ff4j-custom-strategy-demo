package com.omkaras.ff4j.model;

import java.util.List;

public class ToggleResponse {

   List<Toggle> toggleList;

    public ToggleResponse(List<Toggle> toggleList) {
        this.toggleList = toggleList;
    }

    public List<Toggle> getTogglesList() {
        return toggleList;
    }
}
