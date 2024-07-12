package com.headspin.skillbase.catalog.providers;

import com.headspin.skillbase.catalog.domain.CatalogEvent;

import jakarta.transaction.Transactional;

public interface CatalogProducerProvider {

    public void test();
    
    @Transactional
    public void produce(CatalogEvent event);
}
