package com.pawject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.pawject.dto.pet.PetRequestDto;
import com.pawject.dto.pet.PetResponseDto;
import com.pawject.service.pet.PetService;
import com.pawject.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;
    private final UserService userService;

    /** 펫 목록 (마이페이지) */
    @Operation(summary = "내 펫 목록 조회")
    @GetMapping("/mypage")
    public ResponseEntity<List<PetResponseDto>> getMyPets(Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getUserId();
        List<PetResponseDto> pets = petService.getPetsByUserId(userId);
        return ResponseEntity.ok(pets);
    }

    /** 펫 상세 */
    @Operation(summary = "특정 펫 상세 조회")
    @GetMapping("/{petId}")
    public ResponseEntity<PetResponseDto> getPetDetail(
            @Parameter(description = "조회할 펫의 ID", example = "3")
            @PathVariable(name = "petId") Long petId,
            Authentication authentication) {

        Long userId = userService.findByEmail(authentication.getName()).getUserId();
        PetResponseDto pet = petService.getPetDetail(petId, userId);
        return ResponseEntity.ok(pet);
    }


    /** 펫 등록 */
    @Operation(summary = "펫 등록")
    @PostMapping
    public ResponseEntity<PetResponseDto> createPet(@RequestBody PetRequestDto request, Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getUserId();
        PetResponseDto dto = petService.createPet(userId, request);
        return ResponseEntity.ok(dto);
    }

    /** 펫 수정 */
    @Operation(summary = "펫 수정")
    @PutMapping("/{petId}")
    public ResponseEntity<PetResponseDto> updatePet(
            @PathVariable(name = "petId") Long petId,
            @RequestBody PetRequestDto request,
            Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getUserId();
        PetResponseDto dto = petService.updatePet(petId, userId, request);
        return ResponseEntity.ok(dto);
    }


    /** 펫 삭제 */
    @Operation(summary = "펫 삭제")
    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(
            @PathVariable(name = "petId") Long petId,
            Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getUserId();
        petService.deletePet(petId, userId);
        return ResponseEntity.noContent().build();
    }

}