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

    void insertUser(UUID id, MemberUser user);

    void deleteUser(UUID id);

    void updateUser(MemberUser user);

    void insertGroup(UUID id, MemberGroup group);

    void deleteGroup(UUID id);

    void updateGroup(MemberGroup group);

    void test();

}
