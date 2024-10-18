package com.youcode.demo.repository.Implementation;

import com.youcode.demo.config.ManagerFactory;
import com.youcode.demo.entity.RequestStatus;
import com.youcode.demo.repository.RequestStatusInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RequestStatusImpl implements RequestStatusInterface {

    private final EntityManager em;

    public RequestStatusImpl() {
        em = ManagerFactory.getEntityManagerFactory().createEntityManager();
    }

    public RequestStatusImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public Optional<RequestStatus> insertRequestStatus(RequestStatus requestStatus) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(requestStatus);
            tx.commit();
            return Optional.of(requestStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<RequestStatus> getRequestStatus(UUID id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            RequestStatus requestStatus = em.find(RequestStatus.class, id);
            transaction.commit();
            return Optional.of(requestStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<RequestStatus> updateRequestStatus(UUID id, RequestStatus updatedRequestStatus) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            RequestStatus existingRequestStatus = em.find(RequestStatus.class, id);
            if (existingRequestStatus != null) {
                existingRequestStatus.setRequest(updatedRequestStatus.getRequest());
                existingRequestStatus.setStatus(updatedRequestStatus.getStatus());
                existingRequestStatus.setReason(updatedRequestStatus.getReason());
                existingRequestStatus.setUpdatedAt(LocalDateTime.now());

                em.merge(existingRequestStatus);
                tx.commit();
                return Optional.of(existingRequestStatus);
            } else {
                tx.rollback();
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return Optional.empty();
        }
    }
}