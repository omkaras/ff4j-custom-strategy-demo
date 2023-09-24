package com.omkaras.ff4j.service;

import com.omkaras.ff4j.model.ToggleResponse;
import com.omkaras.ff4j.model.Toggle;
import jakarta.servlet.http.HttpServletRequest;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.exception.FeatureNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FF4JService {

    private final FF4j ff4j;

    public FF4JService(FF4j ff4j) {
        this.ff4j = ff4j;
    }

    public ToggleResponse getAllToggles(HttpServletRequest httpServletRequest) {
        initializeFlippingExecutionContent(httpServletRequest);

        Map<String, Feature> featuresFromDb = ff4j.getFeatures();
        List<Toggle> toggleList = new ArrayList<>();
        featuresFromDb.forEach((key, value) -> toggleList.add(new Toggle(key, ff4j.check(key))));
        return new ToggleResponse(toggleList);
    }

    private void initializeFlippingExecutionContent(HttpServletRequest httpServletRequest) {
        FlippingExecutionContext flippingExecutionContext = new FlippingExecutionContext();
        flippingExecutionContext.addValue("Language", httpServletRequest.getHeader("application-language"));
        ThreadLocal<FlippingExecutionContext> threadLocal = new ThreadLocal<>();
        threadLocal.set(flippingExecutionContext);
        ff4j.setFlippingExecutionContext(threadLocal);
    }

}
