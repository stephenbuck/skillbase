package com.headspin.skillbase.workflow.infrastructure.storage;

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
public class WorkflowStorageProviderDummy extends CommonStorageProviderDummy {

    @Inject
    public WorkflowStorageProviderDummy() {
    }
}
