package com.pawject.service.pet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.domain.Pet;
import com.pawject.domain.PetType;
import com.pawject.domain.User;
import com.pawject.dto.pet.PetRequestDto;
import com.pawject.dto.pet.PetResponseDto;
import com.pawject.repository.PetRepository;
import com.pawject.repository.PetTypeRepository;
import com.pawject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final PetTypeRepository petTypeRepository;

    /** 사용자 펫 목록 조회 */
    @Transactional(readOnly = true)
    public List<PetResponseDto> getPetsByUserId(Long userId) {
        return petRepository.findByUserUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(PetResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /** 펫 상세 조회 */
    @Transactional(readOnly = true)
    public PetResponseDto getPetDetail(Long petId, Long userId) {
        Pet pet = petRepository.findByPetIdAndUserUserId(petId, userId)
                .orElseThrow(() -> new IllegalArgumentException("펫을 찾을 수 없습니다."));
        return PetResponseDto.fromEntity(pet);
    }

    /** 펫 등록 */
    public PetResponseDto createPet(Long userId, PetRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        PetType petType = petTypeRepository.findById(request.getPetTypeId())
                .orElseThrow(() -> new IllegalArgumentException("펫 타입을 찾을 수 없습니다."));

        Pet pet = request.toEntity(user, petType);
        Pet saved = petRepository.save(pet);
        return PetResponseDto.fromEntity(saved);
    }

    /** 펫 수정 */
    public PetResponseDto updatePet(Long petId, Long userId, PetRequestDto request) {
        Pet pet = petRepository.findByPetIdAndUserUserId(petId, userId)
                .orElseThrow(() -> new IllegalArgumentException("펫을 찾을 수 없습니다."));

        pet.setPetName(request.getPetName());
        pet.setPetBreed(request.getPetBreed());
        pet.setBirthDate(request.getBirthDate());
        pet.setPfile(request.getPFile());
        pet.setPage(request.getPage());
        pet.setPGender(request.getPGender());

        PetType petType = petTypeRepository.findById(request.getPetTypeId())
                .orElseThrow(() -> new IllegalArgumentException("펫 타입을 찾을 수 없습니다."));
        pet.setPetType(petType);

        return PetResponseDto.fromEntity(pet);
    }

    /** 펫 삭제 */
    public void deletePet(Long petId, Long userId) {
        Pet pet = petRepository.findByPetIdAndUserUserId(petId, userId)
                .orElseThrow(() -> new IllegalArgumentException("펫을 찾을 수 없습니다."));
        petRepository.delete(pet);
    }
}
