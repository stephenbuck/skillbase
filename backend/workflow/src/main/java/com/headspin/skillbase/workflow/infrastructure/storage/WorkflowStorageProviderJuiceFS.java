package com.headspin.skillbase.workflow.infrastructure.storage;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.storage.CommonStorageProviderJuiceFS;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * JuiceFS implementation of the common storage provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class WorkflowStorageProviderJuiceFS extends CommonStorageProviderJuiceFS {

    @Inject
    public WorkflowStorageProviderJuiceFS(
            @ConfigProperty(name = "com.headspin.skillbase.workflow.storage.juicefs.root") final String configRoot)
            throws Exception {
        super(configRoot);
    }
}
