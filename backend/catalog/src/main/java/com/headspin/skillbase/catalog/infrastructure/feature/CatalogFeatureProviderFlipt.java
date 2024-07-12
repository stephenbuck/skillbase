package com.headspin.skillbase.catalog.infrastructure.feature;

import com.headspin.skillbase.catalog.providers.CatalogFeatureProvider;

import io.flipt.api.FliptClient;
import io.flipt.api.evaluation.Evaluation;
import io.flipt.api.evaluation.models.BooleanEvaluationResponse;
import io.flipt.api.evaluation.models.EvaluationRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogFeatureProviderFlipt implements CatalogFeatureProvider {

    public CatalogFeatureProviderFlipt() {
    }

    private FliptClient getClient() {
        return FliptClient.builder()
                .url("http://172.17.0.1:8087")
                .build();
    }

    @Override
    public void test() {
        boolean v = evaluateBoolean("allow-reports", false);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("v = {}", v);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Override
    public boolean evaluateBoolean(String key, boolean def) {
        try {

            FliptClient fliptClient = getClient();

            Evaluation ev = fliptClient.evaluation();

            EvaluationRequest er = EvaluationRequest.builder()
                    .flagKey("allow-reports")
                    .build();

            BooleanEvaluationResponse ber = ev.evaluateBoolean(er);

            return ber.isEnabled();

        } catch (Throwable e) {
            log.info(String.valueOf(e));
            return def;
        } finally {
        }
    }
}
