package com.youcode.demo.service;

import com.youcode.demo.entity.Status;
import com.youcode.demo.repository.StatusInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class StatusService {

    @Inject
    private StatusInterface statusInterface;


    public void addStatus(Status status) throws Exception {
        if (status != null && status.getStatus() != null && !status.getStatus().isEmpty()) {
            Optional<Status> existingStatus = statusInterface.getByStatus(status.getStatus());
            if (existingStatus.isEmpty()) {
                statusInterface.addStatus(status);
            } else {
                throw new Exception("Status already exists.");
            }
        } else {
            throw new Exception("Invalid status information.");
        }
    }


    public Status getStatusById(UUID id) throws Exception {
        if (id == null || id.toString().isEmpty()) {
            throw new Exception("Status ID can't be empty or null.");
        } else {
            return statusInterface.getStatus(id).orElseThrow(() -> new Exception("Status not found."));
        }
    }


    public List<Status> getAllStatuses() {
        return statusInterface.getAllStatus();
    }


    public Status updateStatus(Status status) throws Exception {
        if (status == null || status.getId() == null) {
            throw new Exception("Status ID can't be null.");
        }
        Optional<Status> existingStatus = statusInterface.getStatus(status.getId());
        if (existingStatus.isPresent()) {
            return statusInterface.updateStatus(status).orElseThrow(() -> new Exception("Failed to update status."));
        } else {
            throw new Exception("Status not found.");
        }
    }


    public boolean deleteStatus(UUID id) throws Exception {
        if (id == null || id.toString().isEmpty()) {
            throw new Exception("Status ID can't be null or empty.");
        }
        return statusInterface.removeStatus(id);
    }


    public Status getStatusByStatusName(String status) throws Exception {
        if (status == null || status.isEmpty()) {
            throw new Exception("Status name can't be empty.");
        } else {
            return statusInterface.getByStatus(status).orElseThrow(() -> new Exception("Status not found."));
        }
    }
}
