package com.headspin.skillbase.image.interfaces.service;

import java.io.InputStream;

import com.headspin.skillbase.common.providers.CommonConfigProvider;
import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.common.providers.CommonFeaturesProvider;
import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;

/**
 * Image files service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
public class ImageFilesService {

    @Resource
    private SessionContext ctx;

    @Inject
    private CommonConfigProvider conf;

    @Inject
    private CommonEventsProvider evnt;

    @Inject
    private CommonFeaturesProvider feat;

    @Inject
    private CommonStorageProvider stor;

    @Retry
    @Timeout
    public String uploadImage(@NotNull final InputStream input, @NotNull final MediaType type) throws Exception {
        return stor.uploadObject(input, Long.valueOf(-1), type);
    }

    @Retry
    @Timeout
    public CommonStorageProvider.CommonStorageObject downloadImage(@NotNull final String image_id) throws Exception {
        return stor.downloadObject(image_id);
    }

    @Retry
    @Timeout
    public void deleteImage(@NotNull final String image_id) throws Exception {
        stor.deleteObject(image_id);
    }

    @Operation(summary = "Test the service.")
    public Integer test() {
        log.info("test:");
        conf.test();
        evnt.test();
        feat.test();
        stor.test();
        return 0;
    }
}
