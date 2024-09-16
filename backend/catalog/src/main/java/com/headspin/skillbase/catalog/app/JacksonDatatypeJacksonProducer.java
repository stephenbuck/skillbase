package com.headspin.skillbase.catalog.app;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonDatatypeJacksonProducer implements ContextResolver<ObjectMapper> {

    private final ObjectMapper json;

    public JacksonDatatypeJacksonProducer() {
        this.json = JsonMapper.builder()
                .findAndAddModules()
                .build();
        new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> objectType) {
        return json;
    }
}