package com.youcode.demo.repository;

import com.youcode.demo.entity.Request;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestInterface {

    Optional<Request> addRequest(Request request);
    Optional<Request> getRequest(UUID id);
    Optional<Request> updateRequest(Request request);
    boolean removeRequest(UUID id);
    List<Request> getAllRequests();
}

