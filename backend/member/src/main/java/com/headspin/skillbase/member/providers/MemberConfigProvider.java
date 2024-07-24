package com.headspin.skillbase.member.providers;

import java.util.Optional;

public interface MemberConfigProvider {

    public void test();
    
    public Optional<?> getOptionalValue(String key, Class<?> type);

}
