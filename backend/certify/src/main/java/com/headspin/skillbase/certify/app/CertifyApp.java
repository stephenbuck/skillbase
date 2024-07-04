package com.headspin.skillbase.certify.app;

import java.util.HashSet;
import java.util.Set;

import com.headspin.skillbase.certify.interfaces.rest.CertifyCertsREST;
import com.headspin.skillbase.certify.interfaces.rest.CertifyModelsREST;
import com.headspin.skillbase.certify.interfaces.rest.CertifyProcessesREST;
import com.headspin.skillbase.certify.interfaces.rest.CertifyTasksREST;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/*
 * CertifyApp is the main entry point.
 */

@ApplicationPath("/certify")
public class CertifyApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(CertifyCertsREST.class);
        classSet.add(CertifyModelsREST.class);
        classSet.add(CertifyProcessesREST.class);
        classSet.add(CertifyTasksREST.class);
        return classSet;
    }
}
