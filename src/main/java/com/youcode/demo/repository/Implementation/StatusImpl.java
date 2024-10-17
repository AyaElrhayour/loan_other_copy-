package com.youcode.demo.repository.Implementation;

import com.youcode.demo.config.ManagerFactory;
import com.youcode.demo.entity.Status;
import com.youcode.demo.repository.StatusInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class StatusImpl implements StatusInterface {

    private final EntityManager em;

    public StatusImpl() {
        em = ManagerFactory.getEntityManagerFactory().createEntityManager();
    }

    public StatusImpl(EntityManager em) { this.em = em; }

    @Override
    public Optional<Status> addStatus(Status status) {
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(status);
            transaction.commit();
            return Optional.of(status);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Status> getStatus(UUID id) {
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            Status status = em.find(Status.class, id);
            transaction.commit();
            return Optional.ofNullable(status);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Status> updateStatus(Status status) {
        Status existingStatus = em.find(Status.class, status.getId());
        EntityTransaction transaction = em.getTransaction();
        if (existingStatus != null) {
            existingStatus.setStatus(status.getStatus());

            em.merge(existingStatus);
            transaction.commit();
            return Optional.of(existingStatus);
        }else {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public boolean removeStatus(UUID id) {
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            Status status = em.find(Status.class, id);
            if (status != null) {
                em.remove(status);
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Status> getAllStatus() {
        TypedQuery<Status> query = em.createQuery("SELECT s FROM Status s", Status.class);
        return query.getResultList();
    }

    @Override
    public Optional<Status> getByStatus(String status) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Status> query = em.createQuery("SELECT s FROM Status s WHERE s.status = :status", Status.class);
            query.setParameter("status", status);
            Status result = query.getSingleResult();
            transaction.commit();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return Optional.empty();
        }
    }

}
