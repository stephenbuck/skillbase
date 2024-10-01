package com.headspin.skillbase.member.infrastructure.features;

import com.headspin.skillbase.common.infrastructure.features.CommonFeaturesProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class MemberFeaturesProviderDummy extends CommonFeaturesProviderDummy {

    @Inject
    public MemberFeaturesProviderDummy() {
    }
}
