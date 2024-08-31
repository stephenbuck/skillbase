package com.headspin.skillbase.image.interfaces.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

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
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size) throws Exception {
        return stor.uploadObject(input, size);
    }

    @Retry
    @Timeout
    public InputStream downloadObject(@NotNull final String image_id) throws Exception {
        return stor.downloadObject(image_id);

    }

    @Retry
    @Timeout
    public void deleteObject(@NotNull final String image_id) throws Exception {
        stor.deleteObject(image_id);
    }


    @Operation(summary = "Test service")
    public Integer test() {
        log.info("test:");
        conf.test();
        evnt.test();
        feat.test();
        stor.test();
        return 0;
    }
}
