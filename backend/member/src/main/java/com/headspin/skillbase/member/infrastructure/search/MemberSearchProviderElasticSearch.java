package com.headspin.skillbase.member.infrastructure.search;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.search.CommonSearchProviderElasticSearch;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * ElasticSearch implementation of the member search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class MemberSearchProviderElasticSearch extends CommonSearchProviderElasticSearch {

    @Inject
    public MemberSearchProviderElasticSearch(
            @ConfigProperty(name = "com.headspin.skillbase.member.search.elasticsearch.url") final String configUrl,
            @ConfigProperty(name = "com.headspin.skillbase.member.search.elasticsearch.index") final String configIndex) {
        super(configUrl, configIndex);
    }
}
