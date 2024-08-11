package com.headspin.skillbase.catalog.infrastructure.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import com.headspin.skillbase.catalog.providers.CatalogSearchProvider;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of the catalog search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogSearchProviderElastic implements CatalogSearchProvider {

    private RestClient rest;

    private JacksonJsonpMapper mapper;

    public CatalogSearchProviderElastic() {
        this.rest = RestClient
            .builder(HttpHost.create("http://elastic:9200"))
            .build();
        this.mapper = new JacksonJsonpMapper();
    }
    
    @Override
    public void test() {
        log.info("test:");
        List<String> results = search("cpr", null, null, null);
        for (String result : results) {
            log.info("result = {}", result);
        }
    }

    @Override
    public List<String> search(final String keyword, final String sort, final Integer offset, final Integer limit) {
        try {
            log.info("search({})", keyword);
            try (ElasticsearchTransport transport = new RestClientTransport(rest, mapper)) {

                ElasticsearchClient client = new ElasticsearchClient(transport);

                /*
                client
                    .indices()
                    .create(c -> c
                        .index("skills"));
                */

                SearchResponse<String> search = client
                    .search(s -> s
                    .index("skillbase")
                    .from(Objects.requireNonNullElse(offset, 0))
                    .size(Objects.requireNonNullElse(limit, 10))
                    .query(q -> q
                        .term(t -> t
                            .field("title")
                            .value(v -> v.stringValue(keyword))
                        )),
                String.class);

                /* Use full text .match() */
                
                List<String> results = new ArrayList<String>();

                for (Hit<String> hit: search.hits().hits()) {
                    log.info("hit = {}", hit.source());
                    results.add(hit.source());
                }

                return results;
            }
        }
        catch (IOException e) {
            log.info(String.valueOf(e));
            return null;
        }
    }
}
