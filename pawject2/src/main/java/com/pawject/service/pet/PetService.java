package com.pawject.service.pet;

import java.util.List;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.pet.PetDto;

public interface PetService {
    public int petJoin(MultipartFile file, PetDto pet);
    public List<PetDto> selectPetsByUserId(int userId);
    public PetDto selectPetDetail(int userId, int petId);

    // 펫 정보 수정(사용자)
    public int updatePetByUser(MultipartFile file, PetDto pet, int userId);

    // 펫 정보 수정 상세페이지(관리자)
    public PetDto selectPetDetailByAdmin(int petId);
    
    // 펫 정보 수정(관리자)
    public int updatePetByAdmin(PetDto pet);

    // 펫 삭제(사용자)
    public int deletePetByUser(int petId, int userId);

    // 펫 삭제(관리자)
    public int deletePetByAdmin(int petId);

    // 검색
<<<<<<< HEAD
    public List<PetDto> searchPets(String keyword, String type);
=======
    public List<PetDto> searchPets(String keyword);
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
    
    // paging
    public List<PetDto> selectPet10(int pstartno);
    public int selectTotalPetCnt();

}
