package com.omkaras.ff4j.config;

import org.ff4j.FF4j;
import org.ff4j.springjdbc.store.EventRepositorySpringJdbc;
import org.ff4j.springjdbc.store.FeatureStoreSpringJdbc;
import org.ff4j.springjdbc.store.PropertyStoreSpringJdbc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FF4JConfiguration {

    @Bean
    public FF4j ff4j(DataSource dataSource){
        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new FeatureStoreSpringJdbc(dataSource));
        ff4j.setPropertiesStore(new PropertyStoreSpringJdbc(dataSource));
        ff4j.setEventRepository(new EventRepositorySpringJdbc(dataSource));
        ff4j.audit(true);
        ff4j.setAutocreate(false);
        ff4j.createSchema();
        return ff4j;
    }
}
