package com.youcode.demo.service;

import com.youcode.demo.entity.Request;
import com.youcode.demo.entity.RequestStatus;
import com.youcode.demo.entity.Status;
import com.youcode.demo.repository.Implementation.RequestImpl;
import com.youcode.demo.repository.Implementation.StatusImpl;
import com.youcode.demo.repository.RequestInterface;
import com.youcode.demo.repository.RequestStatusInterface;
import com.youcode.demo.repository.StatusInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RequestService {

    @Inject
    private RequestInterface requestInterface;
    @Inject
    private StatusInterface statusInterface;
    @Inject
    private RequestStatusInterface requestStatusInterface;

    public void addRequest(Request request) throws Exception{
        Optional<Status> status = statusInterface.getByStatus("PENDING");
        if (request != null){
            if(status.isPresent()){
                RequestStatus requestStatus = new RequestStatus();
                requestStatus.setStatus(status.get());
                requestStatus.setRequest(request);
                requestStatus.setUpdatedAt(LocalDateTime.now());

                requestInterface.addRequest(request);
                requestStatusInterface.insertRequestStatus(requestStatus);
            }
        }else {
            throw new RuntimeException("Failed To Insert Request");

        }
    }

    public Request getRequestById(UUID id) throws Exception{
        if (id.toString().isEmpty()){
            throw new Exception("Request id can't be empty");
        }else {
            return requestInterface.getRequest(UUID.fromString(String.valueOf(id))).orElse(null);
        }
    }

    public List<Request> getAllRequests() {
        return requestInterface.getAllRequests();
    }

    //public List<Request> getRequestsByDate(LocalDate date) {    }

    public boolean deleteRequest(UUID id) {
        if (id.toString().isEmpty() || requestInterface.getRequest(id).isEmpty()){
            return false;
        }else {
            return requestInterface.removeRequest(id);
        }
    }

    public Request updateRequest(Request request) throws Exception{
        if (requestInterface.getRequest(request.getId()).isEmpty()){
            throw new Exception("Request id can't be empty");
        }else {
            return requestInterface.updateRequest(request).get();
        }
    }
}

