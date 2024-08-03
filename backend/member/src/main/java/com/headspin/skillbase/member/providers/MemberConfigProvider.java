package com.headspin.skillbase.member.providers;

import java.util.Optional;

/**
 * Member config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface MemberConfigProvider {

    public Optional<?> getOptionalValue(String key, Class<?> type);

    public void test();
    
}
