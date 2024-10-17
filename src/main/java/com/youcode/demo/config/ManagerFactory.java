package com.youcode.demo.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ManagerFactory {

    private static EntityManagerFactory factory;

    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("PERSISTENCE");
        }
        return factory;
    }
}

