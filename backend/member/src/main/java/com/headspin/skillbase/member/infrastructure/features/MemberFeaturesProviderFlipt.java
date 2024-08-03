package com.headspin.skillbase.member.infrastructure.features;

import com.headspin.skillbase.member.providers.MemberFeaturesProvider;

import io.flipt.api.FliptClient;
import io.flipt.api.evaluation.Evaluation;
import io.flipt.api.evaluation.models.BooleanEvaluationResponse;
import io.flipt.api.evaluation.models.EvaluationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of member features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class MemberFeaturesProviderFlipt implements MemberFeaturesProvider {

    /*
    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.member.flipt.url")
    private String url;
    */

    private final FliptClient client;

    public MemberFeaturesProviderFlipt() {
        this.client = FliptClient.builder()
                .url("http://flipt:8080")
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
        } finally {
        }
    }

    @Override
    public void test() {
        log.info("test:");
        log.info("allow-reports = {}", evaluateBoolean("allow-reports", false));
    }
}
