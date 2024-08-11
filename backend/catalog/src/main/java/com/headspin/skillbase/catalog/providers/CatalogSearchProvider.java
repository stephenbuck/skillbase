package com.headspin.skillbase.catalog.providers;

import java.util.List;

/**
 * Catalog search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CatalogSearchProvider {

    public List<String> search(String keyword, String sort, Integer offset, Integer limit);

    public void test();
    
}
