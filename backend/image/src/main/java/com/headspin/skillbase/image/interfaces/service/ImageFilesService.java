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

    @Operation(summary = "Resolve to a path")
    public Path resolvePath(@NotNull final UUID home, @NotNull final UUID uuid) {
        return stor.resolvePath(home, uuid);
    }

    @Operation(summary = "Resolve to a file")
    public File resolveFile(@NotNull final UUID home, @NotNull final UUID uuid) {
        return stor.resolveFile(home, uuid);
    }

    @Retry
    @Timeout
    @Operation(summary = "Upload a file")
    public void upload(@NotNull final UUID home, @NotNull final InputStream src, @NotNull final UUID dst)
            throws IOException {
        stor.upload(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "Download a file")
    public void download(@NotNull final UUID home, @NotNull final UUID src, @NotNull final OutputStream dst)
            throws IOException {
        stor.download(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "Copy a file")
    public void copy(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        stor.copy(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "Move a file")
    public void move(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        stor.move(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "Delete a file")
    public void delete(@NotNull final UUID home, @NotNull final UUID uuid) throws IOException {
        stor.delete(home, uuid);
    }

    @Retry
    @Timeout
    @Operation(summary = "Rename a file")
    public void rename(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        stor.rename(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "List files")
    public List<String> list(@NotNull final UUID home, @NotNull final UUID uuid) throws IOException {
        return stor.list(home, uuid);
    }



    public void uploadObject(String id, InputStream input, Long size) throws Exception {
        stor.uploadObject(id, input, size);
    }

    public InputStream downloadObject(String id) throws Exception {
        return stor.downloadObject(id);

    }

    public void deleteObject(String id) throws Exception {
        stor.deleteObject(id);

    }

    public List<String> listObjects() throws Exception {
        return stor.listObjects();
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
