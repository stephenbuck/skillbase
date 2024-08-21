package com.headspin.skillbase.storage.infrastructure.features;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.storage.providers.StorageFeaturesProvider;

import io.flipt.api.FliptClient;
import io.flipt.api.evaluation.Evaluation;
import io.flipt.api.evaluation.models.BooleanEvaluationResponse;
import io.flipt.api.evaluation.models.EvaluationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of Storage features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class StorageFeaturesProviderFlipt implements StorageFeaturesProvider {

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.storage.flipt.url")
    private String url;

    private final FliptClient client;

    public StorageFeaturesProviderFlipt() {
        this.client = FliptClient.builder()
                .url("http://flipt:8087")
                .build();
    }

    @Override
    public boolean evaluateBoolean(String key, boolean def) {
        try {

            Evaluation ev = client.evaluation();

            EvaluationRequest er = EvaluationRequest.builder()
                    .flagKey("allow-reports")
                    .build();

            BooleanEvaluationResponse ber = ev.evaluateBoolean(er);

            return ber.isEnabled();

        } catch (Throwable e) {
            log.info(String.valueOf(e));
            return def;
        }
    }

    @Override
    public void test() {
        log.info("test:");
        log.info("allow-reports = {}", evaluateBoolean("allow-reports", false));
    }
}
