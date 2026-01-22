package com.pawject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PETTYPE")
public class PetType {

    @Id
    @Column(name = "PETTYPEID")
    private Long petTypeId;

    @Column(name = "PETTYPENAME", nullable = false, length = 100)
    private String petTypeName;

    protected PetType() {
        // JPA 기본 생성자
    }

    public PetType(Long petTypeId, String petTypeName) {
        this.petTypeId = petTypeId;
        this.petTypeName = petTypeName;
    }
}
