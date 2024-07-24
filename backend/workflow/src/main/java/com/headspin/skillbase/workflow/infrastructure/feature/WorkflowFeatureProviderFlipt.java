package com.headspin.skillbase.workflow.infrastructure.feature;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.workflow.providers.WorkflowFeatureProvider;

import io.flipt.api.FliptClient;
import io.flipt.api.evaluation.Evaluation;
import io.flipt.api.evaluation.models.BooleanEvaluationResponse;
import io.flipt.api.evaluation.models.EvaluationRequest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of workflow feature provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class WorkflowFeatureProviderFlipt implements WorkflowFeatureProvider {

    /*
    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.workflow.flipt.url")
    private String url;
    */

    private final FliptClient client;

    public WorkflowFeatureProviderFlipt() {
        this.client = FliptClient.builder()
                .url("http://flipt:8087")
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
}
