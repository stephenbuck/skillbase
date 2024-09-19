package com.headspin.skillbase.common.infrastructure.features;

import com.headspin.skillbase.common.providers.CommonFeaturesProvider;

import io.flipt.api.FliptClient;
import io.flipt.api.evaluation.Evaluation;
import io.flipt.api.evaluation.models.BooleanEvaluationResponse;
import io.flipt.api.evaluation.models.EvaluationRequest;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonFeaturesProviderFlipt implements CommonFeaturesProvider {

    private final String namespace;
    private final FliptClient client;

    @Inject
    public CommonFeaturesProviderFlipt(
            final String configUrl,
            final String configNamespace) {
        this.namespace = configNamespace;
        this.client = FliptClient.builder()
                .url(configUrl)
                .build();
    }

    @Override
    public boolean evaluateBoolean(@NotNull final String flag, final boolean def) {
        try {
            Evaluation ev = client.evaluation();
            EvaluationRequest er = EvaluationRequest.builder()
                    .namespaceKey(this.namespace)
                    .flagKey(flag)
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
    }
}
