package com.youcode.demo.service;

import com.youcode.demo.entity.RequestStatus;
import com.youcode.demo.entity.Status;
import com.youcode.demo.repository.RequestStatusInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RequestStatusService {

    @Inject
    private RequestStatusInterface requestStatusInterface;


    public void addRequestStatus(RequestStatus requestStatus) throws Exception {
        if (requestStatus != null) {
            requestStatusInterface.insertRequestStatus(requestStatus).orElseThrow(() -> new Exception("Failed to insert request status."));
        } else {
            throw new Exception("Invalid request status information.");
        }
    }


    public RequestStatus getRequestStatusById(UUID id) throws Exception {
        if (id == null || id.toString().isEmpty()) {
            throw new Exception("Request status ID can't be empty or null.");
        } else {
            return requestStatusInterface.getRequestStatus(id).orElseThrow(() -> new Exception("Request status not found."));
        }
    }


    public void updateRequestStatus(UUID id, RequestStatus requestStatus) throws Exception {
        if (id == null || id.toString().isEmpty()) {
            throw new Exception("Request status ID can't be empty or null.");
        }
        if (requestStatus == null) {
            throw new Exception("Invalid request status information.");
        }
        requestStatusInterface.updateRequestStatus(id, requestStatus)
                .orElseThrow(() -> new Exception("Failed to update request status."));
    }
}