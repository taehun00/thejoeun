package com.pawject.service.pet;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.pet.PetDto;

public interface PetService {
	int petJoin(MultipartFile file, PetDto pet);

    List<PetDto> selectPetsByUserId(int userId);

    PetDto selectPetDetail(int userId, int petId);

    // 펫 정보 수정(사용자)
    int updatePetByUser(MultipartFile file, PetDto pet, int userId);

    // 펫 정보 수정 상세페이지(관리자)
    PetDto selectPetDetailByAdmin(int petId);

    // 펫 정보 수정(관리자)
    int updatePetByAdmin(PetDto pet);

    // 펫 삭제(사용자)
    int deletePetByUser(int petId, int userId);

    // 펫 삭제(관리자)
    int deletePetByAdmin(int petId);

    // 검색
    List<PetDto> searchPets(String keyword, String type);

    // 페이징
    List<PetDto> selectPet10(int pstartno);

    int selectTotalPetCnt();

}
