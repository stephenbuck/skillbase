package com.headspin.skillbase.common.infrastructure.registry;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.headspin.skillbase.common.providers.CommonRegistryProvider;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Etcd implementation of the common registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonRegistryProviderEtcd implements CommonRegistryProvider {

    private final Client client;

    public CommonRegistryProviderEtcd(
            final String configEndpoints,
            final String configUsername,
            final String configPassword,
            final String configNamespace) {
        this.client = Client
                .builder()
                .endpoints(configEndpoints)
                .user(ByteSequence.from(configUsername.getBytes()))
                .password(ByteSequence.from(configPassword
                        .getBytes()))
                .namespace(ByteSequence.from(configNamespace.getBytes()))
                .build();
    }

    @Override
    public String lookup(final String key) throws Exception {
        final ByteSequence keyBytes = ByteSequence.from(key.getBytes());
        final CompletableFuture<GetResponse> future = client.getKVClient().get(keyBytes);
        final GetResponse response = future.get();
        final List<KeyValue> pairs = response.getKvs();
        return pairs.get(0).getValue().toString();
    }

    @Override
    public boolean register(final String key, final String val) throws Exception {
        final ByteSequence keyBytes = ByteSequence.from(key.getBytes());
        final ByteSequence valBytes = ByteSequence.from(val.getBytes());
        client.getKVClient().put(keyBytes, valBytes);
        return true;
    }

    @Override
    public boolean delete(final String key) throws Exception {
        final ByteSequence keyBytes = ByteSequence.from(key.getBytes());
        client.getKVClient().delete(keyBytes).get();
        return true;
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
