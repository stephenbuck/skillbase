package com.headspin.skillbase.member.app;

import java.util.HashSet;
import java.util.Set;

import com.headspin.skillbase.member.interfaces.rest.MemberUsersREST;
import com.headspin.skillbase.member.interfaces.rest.MemberAchievementsREST;
import com.headspin.skillbase.member.interfaces.rest.MemberGroupsREST;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/*
 * MemberApp is the main entry point.
 */

@ApplicationPath("/member")
public class MemberApp extends Application {

    public MemberApp() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(MemberApp.class);
//        classSet.add(MemberAppHealth.class);
        classSet.add(MemberAchievementsREST.class);
        classSet.add(MemberGroupsREST.class);
        classSet.add(MemberUsersREST.class);
        return classSet;
    }
}
