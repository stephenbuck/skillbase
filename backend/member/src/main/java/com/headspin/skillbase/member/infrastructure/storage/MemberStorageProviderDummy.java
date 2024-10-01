package com.headspin.skillbase.member.infrastructure.storage;

import com.headspin.skillbase.common.infrastructure.storage.CommonStorageProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common storage provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class MemberStorageProviderDummy extends CommonStorageProviderDummy {

    @Inject
    public MemberStorageProviderDummy() {
    }
}
