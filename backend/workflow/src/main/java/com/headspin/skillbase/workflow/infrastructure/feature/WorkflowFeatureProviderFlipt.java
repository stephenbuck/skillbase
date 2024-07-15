package com.headspin.skillbase.workflow.infrastructure.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.headspin.skillbase.workflow.providers.WorkflowFeatureProvider;

import io.flipt.api.FliptClient;
import io.flipt.api.evaluation.Evaluation;
import io.flipt.api.evaluation.models.BooleanEvaluationResponse;
import io.flipt.api.evaluation.models.EvaluationRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of workflow feature provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class WorkflowFeatureProviderFlipt implements WorkflowFeatureProvider {

    public WorkflowFeatureProviderFlipt() {
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
