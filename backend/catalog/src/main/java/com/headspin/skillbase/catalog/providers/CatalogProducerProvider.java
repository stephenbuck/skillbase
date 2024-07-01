package com.headspin.skillbase.catalog.providers;

import com.headspin.skillbase.catalog.domain.CatalogEvent;

import jakarta.transaction.Transactional;

public interface CatalogProducerProvider {

    @Transactional
    public void produce(CatalogEvent event);
}
