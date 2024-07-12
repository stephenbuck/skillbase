package com.headspin.skillbase.catalog.providers;

import jakarta.transaction.Transactional;

public interface CatalogConsumerProvider {

    public void test();
    
    @Transactional
    public void consume();
}
