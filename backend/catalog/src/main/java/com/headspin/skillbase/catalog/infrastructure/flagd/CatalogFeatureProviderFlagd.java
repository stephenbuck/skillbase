package com.headspin.skillbase.catalog.infrastructure.flagd;

import com.headspin.skillbase.catalog.providers.CatalogFeatureProvider;

import dev.openfeature.contrib.providers.flagd.Config;
import dev.openfeature.contrib.providers.flagd.FlagdOptions;
import dev.openfeature.contrib.providers.flagd.FlagdProvider;
import dev.openfeature.contrib.providers.flagd.FlagdOptions.FlagdOptionsBuilder;
import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.OpenFeatureAPI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogFeatureProviderFlagd implements CatalogFeatureProvider {

    private OpenFeatureAPI api = OpenFeatureAPI.getInstance();

    public CatalogFeatureProviderFlagd() {
    }

    //   // run the myClientOnReadyHandler function when the PROVIDER_READY event is fired
    //   // client.addHandler(ProviderEvents.Ready, myClientOnReadyHandler);

    @Override
    public String getValue(String key) {

        try {
            FlagdOptionsBuilder bldr = FlagdOptions.builder();

            FlagdOptions opts = bldr
            .resolverType(Config.Resolver.RPC) // .IN_PROCESS)
            .host("172.17.0.1")
            .maxEventStreamRetries(10)
            .port(8015)
            .deadline(2000)
//            .offlineFlagSourcePath("PATH")
            .build();

            FlagdProvider flagd = new FlagdProvider(opts);

            
            api.setProvider(flagd);

            Client client = api.getClient(); //"my-app");

            // get a string value
            String stringValue = client.getStringValue(key, "default");

            return stringValue;

        }
        catch (Exception e) {
            log.info(String.valueOf(e));
        }

        return null;
    }
}
