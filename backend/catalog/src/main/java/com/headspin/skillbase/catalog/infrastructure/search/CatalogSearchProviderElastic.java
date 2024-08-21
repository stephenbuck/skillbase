package com.headspin.skillbase.catalog.infrastructure.search;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.headspin.skillbase.catalog.providers.CatalogSearchProvider;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
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

    public CatalogSearchProviderElastic() {
    }
    
    @Override
    public void test() {
        log.info("test:");
        List<String> results = search("cpr", null, null, null);
        results.forEach(System.out::println);
    }

    @Override
    public List<String> search(final String keyword, final String sort, final Integer offset, final Integer limit) {

        log.info("search({})", keyword);

        final RestClient rest = RestClient
            .builder(HttpHost.create("http://elastic:9200"))
            .build();
        
        final JacksonJsonpMapper mapper = new JacksonJsonpMapper();
        
        try (ElasticsearchTransport transport = new RestClientTransport(rest, mapper)) {

            ElasticsearchClient client = new ElasticsearchClient(transport);

            SearchResponse<ObjectNode> search = client
                .search(s -> s
                .index("skillbase")
                    .from(Objects.requireNonNullElse(offset, 0))
                    .size(Objects.requireNonNullElse(limit, 10))
                .query(q -> q
                    .term(t -> t
                        .field("title")
                        .value(v -> v.stringValue(keyword))
                    )),
                    ObjectNode.class);

            /* Use full text .match() */
            
            HitsMetadata<ObjectNode> meta = search.hits();

            List<String> results = meta.hits().stream().map(
                h -> String.valueOf(h.source())
            ).collect(Collectors.toList());

            return results;
        }
        catch (IOException e) {
            log.info(String.valueOf(e));
            return null;
        }
    }
}
