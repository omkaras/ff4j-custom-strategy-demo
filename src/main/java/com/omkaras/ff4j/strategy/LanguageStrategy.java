package com.omkaras.ff4j.strategy;

import org.ff4j.core.FeatureStore;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.AbstractFlipStrategy;

import java.util.*;

public class LanguageStrategy extends AbstractFlipStrategy {
    public static final String ACCEPTED_LANGUAGES = "acceptedLanguages";
    private static final List<String> acceptedLanguages = new ArrayList<>();
    private String rawClientList = null;

    @Override
    public void init(String featureName, Map<String, String> initParams) {
        super.init(featureName, initParams);
        if (initParams != null && initParams.containsKey(ACCEPTED_LANGUAGES)) {
            this.rawClientList = initParams.get(ACCEPTED_LANGUAGES);
        }
        acceptedLanguages.addAll(Arrays.asList(rawClientList.split(",")));
    }
    @Override
    public boolean evaluate(String featureName, FeatureStore store, FlippingExecutionContext executionContext) {
        String lang = executionContext.getString("Language");
        return Objects.nonNull(lang) && acceptedLanguages.contains(lang);
    }
}


