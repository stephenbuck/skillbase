package com.headspin.skillbase.catalog.infrastructure.search;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.search.CommonSearchProviderOpenSearch;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * OpenSearch implementation of the catalog search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogSearchProviderOpenSearch extends CommonSearchProviderOpenSearch {

    @Inject
    public CatalogSearchProviderOpenSearch(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.search.opensearch.url") final String configUrl,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.search.opensearch.index") final String configIndex) {
        super(configUrl, configIndex);
    }
}
