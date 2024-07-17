package com.headspin.skillbase.catalog.providers;

import com.headspin.skillbase.common.events.CatalogEvent;

import jakarta.transaction.Transactional;

public interface CatalogProducerProvider {

    public void test();
    
    @Transactional
    public void produce(CatalogEvent event);
}
