package com.headspin.skillbase.storage.interfaces.service;

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
 * Storage files service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
public class StorageFilesService {

    @Resource
    private SessionContext ctx;

    @Inject
    private CommonConfigProvider conf;

    @Inject
    private CommonEventsProvider evnt;

    @Inject
    private CommonFeaturesProvider feat;

    @Inject
    private CommonStorageProvider fsys;

    @Operation(summary = "Resolve to a path")
    public Path resolvePath(@NotNull final UUID home, @NotNull final UUID uuid) {
        return fsys.resolvePath(home, uuid);
    }

    @Operation(summary = "Resolve to a file")
    public File resolveFile(@NotNull final UUID home, @NotNull final UUID uuid) {
        return fsys.resolveFile(home, uuid);
    }

    @Retry
    @Timeout
    @Operation(summary = "Upload a file")
    public void upload(@NotNull final UUID home, @NotNull final InputStream src, @NotNull final UUID dst)
            throws IOException {
        fsys.upload(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "Download a file")
    public void download(@NotNull final UUID home, @NotNull final UUID src, @NotNull final OutputStream dst)
            throws IOException {
        fsys.download(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "Copy a file")
    public void copy(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        fsys.copy(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "Move a file")
    public void move(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        fsys.move(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "Delete a file")
    public void delete(@NotNull final UUID home, @NotNull final UUID uuid) throws IOException {
        fsys.delete(home, uuid);
    }

    @Retry
    @Timeout
    @Operation(summary = "Rename a file")
    public void rename(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        fsys.rename(home, src, dst);
    }

    @Retry
    @Timeout
    @Operation(summary = "List files")
    public List<String> list(@NotNull final UUID home, @NotNull final UUID uuid) throws IOException {
        return fsys.list(home, uuid);
    }



    public void uploadObject(String id, InputStream input, Long size) throws Exception {
        fsys.uploadObject(id, input, size);
    }

    public InputStream downloadObject(String id) throws Exception {
        return fsys.downloadObject(id);

    }

    public void deleteObject(String id) throws Exception {
        fsys.deleteObject(id);

    }

    public List<String> listObjects() throws Exception {
        return fsys.listObjects();
    }



    @Operation(summary = "Test service")
    public Integer test() {
        log.info("test:");
        conf.test();
        evnt.test();
        feat.test();
        fsys.test();
        return 0;
    }
}
