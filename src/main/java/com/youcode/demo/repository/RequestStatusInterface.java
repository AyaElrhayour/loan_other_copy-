package com.youcode.demo.repository;

import com.youcode.demo.entity.RequestStatus;

import java.util.Optional;
import java.util.UUID;

public interface RequestStatusInterface {
 Optional<RequestStatus>insertRequestStatus(RequestStatus requestStatus);
 Optional<RequestStatus> getRequestStatus(UUID id);
 Optional<RequestStatus> updateRequestStatus(UUID id, RequestStatus updatedRequestStatus);
}
