package com.headspin.skillbase.storage.providers;

import java.util.List;

/**
 * Storage search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface StorageSearchProvider {

    public List<String> search(String keyword, String sort, Integer offset, Integer limit);

    public void test();
    
}
