package com.headspin.skillbase.workflow.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.workflow.domain.Process;
import com.headspin.skillbase.workflow.domain.ProcessRepo;

import jakarta.inject.Inject;

public class ProcessService {

    @Inject
    private ProcessRepo repo;

    public Optional<Process> findById(int id) {
        return repo.findById(id);
    }

    public List<Process> findAll() {
        return repo.findAll();
    }
}
