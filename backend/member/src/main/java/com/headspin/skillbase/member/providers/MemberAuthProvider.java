package com.headspin.skillbase.member.providers;

import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberUser;

/**
 * Member auth provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

 public interface MemberAuthProvider {

    public void insertUser(UUID id, MemberUser user);

    public void deleteUser(UUID id);

    public void updateUser(MemberUser user);

    public void insertGroup(UUID id, MemberGroup group);

    public void deleteGroup(UUID id);

    public void updateGroup(MemberGroup group);

    public void test();

}
