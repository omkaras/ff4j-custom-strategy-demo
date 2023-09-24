package com.omkaras.ff4j.controller;

import com.omkaras.ff4j.model.Toggle;
import com.omkaras.ff4j.model.ToggleResponse;
import com.omkaras.ff4j.service.FF4JService;
import jakarta.servlet.http.HttpServletRequest;
import org.ff4j.FF4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FF4JController.class)
public class FF4JControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FF4JService service;

    @MockBean
    FF4j ff4j;

    @Test
    public void shouldReturnListOfToggles() throws Exception {
        List<Toggle> toggleList = new ArrayList<>();
        toggleList.add(new Toggle("show-header", true));
        toggleList.add(new Toggle("show-footer", false));
        ToggleResponse response = new ToggleResponse(toggleList);
        when(service.getAllToggles(any())).thenReturn(response);

        ResultActions resultActions = mvc.perform(get("/ff4j/v1/toggles").with(request -> {
            request.setAttribute("attrName", "attrValue");
            return request;
        }));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.togglesList[0].name", is("show-header")))
                .andExpect(jsonPath("$.togglesList[0].value", is(true)));
    }

}
