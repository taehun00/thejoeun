package com.pawject.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "PETS")
@SequenceGenerator(
        name = "PET_SEQ_GENERATOR",
        sequenceName = "PET_SEQ",
        allocationSize = 1
)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PET_SEQ_GENERATOR")
    @Column(name = "PETID")
    private Long petId;

    /**
     * USER (1) : PET (N)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID", nullable = false)
    private User user;

    @Column(name = "PETNAME", nullable = false, length = 100)
    private String petName;

    @Column(name = "PETBREED", length = 100)
    private String petBreed;

    /**
     * VARCHAR2(100)이지만 날짜 의미 → LocalDate 권장
     */
    @Column(name = "BIRTHDATE", length = 100)
    private String birthDate;

    /**
     * PET (N) : PETTYPE (1)
     * FK: PETS.PETTYPEID → PETTYPE.PETTYPEID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PETTYPEID", nullable = false)
    private PetType petType;

    @Column(name = "PFILE", length = 255)
    private String pfile;

    @Column(name = "CREATEDAT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "GENDER", length = 10)
    private String gender;

    protected Pet() {
        // JPA 기본 생성자
    }

    public Pet(User user, String petName) {
        this.user = user;
        this.petName = petName;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
