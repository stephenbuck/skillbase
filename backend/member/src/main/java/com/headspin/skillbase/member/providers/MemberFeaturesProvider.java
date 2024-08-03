package com.headspin.skillbase.member.providers;

/**
 * Member features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

 public interface MemberFeaturesProvider {

    public boolean evaluateBoolean(String key, boolean def);
    
    public void test();

}
