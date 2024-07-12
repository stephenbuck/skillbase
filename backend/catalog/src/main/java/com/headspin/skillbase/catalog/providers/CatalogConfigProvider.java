package com.headspin.skillbase.catalog.providers;

import java.util.Optional;

public interface CatalogConfigProvider {

    public void test();
    
    public Optional<String> getValue(String key, Class type);

}
