package com.pawject.service.pet;

import java.util.List;
import java.util.Map;

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
    public List<PetDto> searchPets(String keyword, String type);
    
    // paging
    public List<PetDto> selectPet10(int pstartno);
    public int selectTotalPetCnt();

}
