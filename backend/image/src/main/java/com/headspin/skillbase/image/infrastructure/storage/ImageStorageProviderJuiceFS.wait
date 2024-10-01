package com.headspin.skillbase.image.infrastructure.storage;

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
public class ImageStorageProviderJuiceFS extends CommonStorageProviderJuiceFS {

    @Inject
    public ImageStorageProviderJuiceFS(
            @ConfigProperty(name = "com.headspin.skillbase.image.storage.juicefs.root") final String configRoot)
            throws Exception {
        super(configRoot);
    }
}
