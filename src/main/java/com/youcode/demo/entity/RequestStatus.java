package com.youcode.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requestStatus")
public class RequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "requestId")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "statusId")
    private Status status;

    @Column
    private String reason;

    @Column(name = "UpdatedAt")
    private LocalDateTime UpdatedAt;
}