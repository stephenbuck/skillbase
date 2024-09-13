package com.headspin.skillbase.member.infrastructure.search;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.client.RestClient;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.headspin.skillbase.common.providers.CommonSearchProvider;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of the catalog search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class MemberSearchProviderElastic implements CommonSearchProvider {

    private final String url;
    private final String index;
    private final RestClient rest;
    private final JacksonJsonpMapper mapper;

    @Inject
    public MemberSearchProviderElastic(
        @ConfigProperty(name = "com.headspin.skillbase.catalog.search.elastic.url") final String configUrl,
        @ConfigProperty(name = "com.headspin.skillbase.catalog.search.elastic.index") final String configIndex
    ) {
        this.url = configUrl;
        this.index = configIndex;
        this.rest = RestClient
            .builder(HttpHost.create(url))
            .build();
        this.mapper = new JacksonJsonpMapper();        
    }

    @Override
    public List<String> search(@NotNull final String keyword, final String sort, final Integer offset, final Integer limit) {

        log.info("search({})", keyword);

        try (final ElasticsearchTransport transport = new RestClientTransport(rest, mapper)) {

            final ElasticsearchClient client = new ElasticsearchClient(transport);

            final SearchResponse<ObjectNode> search = client
                .search(s -> s
                .index(index)
                    .from(Objects.requireNonNullElse(offset, 0))
                    .size(Objects.requireNonNullElse(limit, 10))
                .query(q -> q
                    .term(t -> t
                        .field("title")
                        .value(v -> v.stringValue(keyword))
                    )),
                    ObjectNode.class);

            /* Use full text .match() */
            
            final HitsMetadata<ObjectNode> meta = search.hits();

            List<String> results = meta.hits().stream().map(
                h -> String.valueOf(h.source()))
                    .collect(Collectors.toList());

            return results;
        }
        catch (IOException e) {
            log.info(String.valueOf(e));
            return null;
        }
    }

    @Override
    public void test() {
        log.info("test:");
        final List<String> results = search("cpr", null, null, null);
        results.forEach(System.out::println);
    }
}
