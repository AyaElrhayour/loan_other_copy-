package com.youcode.demo.entity;

import com.youcode.demo.enums.Title;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String project;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    @Positive
    @Min(5000)
    @Max(400000)
    private Double amount;

    @Column(nullable = false)
    @Positive
    @Min(6)
    @Max(120)
    private Integer period;

    @Column(nullable = false)
    private Double monthlyPayment;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Title title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String idCard;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private LocalDate hiringDate;

    @Column(nullable = false)
    private Double monthlyIncome;

    @Column(nullable = false)
    private Boolean oldLoan;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<RequestStatus> requestStatus;

}
