package com.youcode.demo.repository;


import com.youcode.demo.entity.Status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StatusInterface {

    Optional<Status> addStatus(Status status);
    Optional<Status> getStatus(UUID id);
    Optional<Status> updateStatus(Status status);
    boolean removeStatus(UUID id);
    List<Status> getAllStatus();
    Optional<Status> getByStatus(String status);
}
