package com.headspin.skillbase.image.infrastructure.features;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.providers.CommonFeaturesProvider;

import io.flipt.api.FliptClient;
import io.flipt.api.evaluation.Evaluation;
import io.flipt.api.evaluation.models.BooleanEvaluationResponse;
import io.flipt.api.evaluation.models.EvaluationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of Image features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class ImageFeaturesProviderFlipt implements CommonFeaturesProvider {

    private final String namespace;
    private final FliptClient client;

    @Inject
    public ImageFeaturesProviderFlipt(
        @ConfigProperty(name = "com.headspin.skillbase.image.flipt.url") String configUrl,
        @ConfigProperty(name = "com.headspin.skillbase.image.flipt.namespace") String configNamespace   
    ) {
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
                    .namespaceKey(namespace)
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
