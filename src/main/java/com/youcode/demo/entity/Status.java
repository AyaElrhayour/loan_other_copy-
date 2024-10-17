package com.youcode.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String status;

   @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<RequestStatus> requestStatus;
}

