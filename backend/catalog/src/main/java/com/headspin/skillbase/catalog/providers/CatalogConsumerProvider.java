package com.headspin.skillbase.catalog.providers;

import jakarta.transaction.Transactional;

public interface CatalogConsumerProvider {

    @Transactional
    public void consume();
}
