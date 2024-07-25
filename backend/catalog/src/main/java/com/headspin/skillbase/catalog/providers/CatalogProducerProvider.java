package com.headspin.skillbase.catalog.providers;

import com.headspin.skillbase.common.events.CatalogEvent;

public interface CatalogProducerProvider {

    public void test();
    
    public void produce(CatalogEvent event);
}
