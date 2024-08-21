package com.headspin.skillbase.storage.interfaces.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import com.headspin.skillbase.storage.providers.StorageConfigProvider;
import com.headspin.skillbase.storage.providers.StorageEventsProvider;
import com.headspin.skillbase.storage.providers.StorageFeaturesProvider;
import com.headspin.skillbase.storage.providers.StorageFilesProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

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
// @DeclareRoles(SecurityRole.list())
public class StorageFilesService {

    @Resource
    private SessionContext ctx;

    @Inject
    private StorageConfigProvider conf;

    @Inject
    private StorageEventsProvider evnt;

    @Inject
    private StorageFeaturesProvider feat;

    @Inject
    private StorageFilesProvider fsys;

    public Path resolvePath(@NotNull final UUID home, @NotNull final UUID uuid) {
        return fsys.resolvePath(home, uuid);
    }

    public File resolveFile(@NotNull final UUID home, @NotNull final UUID uuid) {
        return fsys.resolveFile(home, uuid);
    }

    public void upload(@NotNull final UUID home, @NotNull final InputStream src, @NotNull final UUID dst) throws IOException {
        fsys.upload(home, src, dst);
    }

    public void download(@NotNull final UUID home, @NotNull final UUID src, @NotNull final OutputStream dst) throws IOException {
        fsys.download(home, src, dst);
    }

    public void copy(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        fsys.copy(home, src, dst);
    }

    public void move(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        fsys.move(home, src, dst);
    }

    public void delete(@NotNull final UUID home, @NotNull final UUID uuid) throws IOException {
        fsys.delete(home, uuid);
    }

    public void rename(@NotNull final UUID home, @NotNull final UUID src, @NotNull final UUID dst) throws IOException {
        fsys.rename(home, src, dst);
    }

    public List<String> list(@NotNull final UUID home, @NotNull final UUID uuid) throws IOException {
        return fsys.list(home, uuid);
    }

    public Integer test() {
        log.info("test:");
        conf.test();
        evnt.test();
        feat.test();
        fsys.test();
        return 0;
    }
}
