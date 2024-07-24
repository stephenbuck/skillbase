package com.headspin.skillbase.member.infrastructure.feature;

import com.headspin.skillbase.member.providers.MemberFeatureProvider;

import io.flipt.api.FliptClient;
import io.flipt.api.evaluation.Evaluation;
import io.flipt.api.evaluation.models.BooleanEvaluationResponse;
import io.flipt.api.evaluation.models.EvaluationRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of member feature provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

 @Slf4j
public class MemberFeatureProviderFlipt implements MemberFeatureProvider {

    /*
    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.member.flipt.url")
    private String url;
    */

    private final FliptClient client;

    public MemberFeatureProviderFlipt() {
        this.client = FliptClient.builder()
                .url("http://flipt:8080")
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
        } finally {
        }
    }
}
