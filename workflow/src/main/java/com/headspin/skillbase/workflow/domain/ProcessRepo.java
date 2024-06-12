package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;

public interface ProcessRepo {

    Optional<Process> findById(int id);

    List<Process> findAll();
}