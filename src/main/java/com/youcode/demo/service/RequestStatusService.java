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


    public void updateRequestStatus(UUID id) throws Exception {
        if (requestId == null || statusId == null) {
            throw new Exception("Request ID and Status ID can't be null.");
        }
        Optional<RequestStatus> existingRequestStatus = requestStatusInterface.getRequestStatus(requestId);

        if (existingRequestStatus.isPresent()) {
            boolean removed = requestStatusInterface.removeRequestStatus(statusId);
            if (!removed) {
                throw new Exception("Failed to remove the existing status.");
            }

            RequestStatus newRequestStatus = new RequestStatus();

            newRequestStatus.setRequest(existingRequestStatus.get().getRequest());
            Status newStatus = new Status();
            newStatus.setId(statusId);
            newRequestStatus.setStatus(newStatus);
            newRequestStatus.setUpdatedAt(LocalDateTime.now());

            requestStatusInterface.insertRequestStatus(newRequestStatus);
        } else {
            throw new Exception("Request status not found for the provided request ID.");
        }
    }
}