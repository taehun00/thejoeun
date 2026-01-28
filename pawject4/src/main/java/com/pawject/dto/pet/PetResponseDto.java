package com.pawject.dto.pet;

import java.time.LocalDateTime;

import com.pawject.domain.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDto {
	private Long petId;
    private Long userId;
    private String petName;
    private String petBreed;
    private String birthDate;
    private Long petTypeId;
    private String pFile;
    private LocalDateTime createdAt;
    private Integer page;
    private String pGender;

    // 엔티티 → DTO 변환
    public static PetResponseDto fromEntity(Pet pet) {
        PetResponseDto dto = new PetResponseDto();
        dto.petId = pet.getPetId();
        dto.userId = pet.getUser().getUserId();
        dto.petName = pet.getPetName();
        dto.petBreed = pet.getPetBreed();
        dto.birthDate = pet.getBirthDate();
        dto.petTypeId = pet.getPetType() != null ? pet.getPetType().getPetTypeId() : null;
        dto.pFile = pet.getPfile();
        dto.createdAt = pet.getCreatedAt();
        dto.page = pet.getPage();
        dto.pGender = pet.getPGender();
        return dto;
    }


}
