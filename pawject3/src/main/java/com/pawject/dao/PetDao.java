package com.pawject.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pawject.dto.pet.PetDto;

@Mapper
public interface PetDao {
	// 펫 등록
    int petJoin(PetDto pet);

    // 펫 페이지 (사용자)
    List<PetDto> selectPetsByUserId(int userId);

    // 펫 상세페이지 (사용자)
    PetDto selectPetDetail(@Param("userId") int userId,
                           @Param("petId") int petId);

    // 펫 정보 수정 (사용자)
    int updatePetByUser(@Param("pet") PetDto pet,
                        @Param("userId") int userId);

    // 펫 상세 조회 (관리자)
    PetDto selectPetDetailByAdmin(int petId);

    // 펫 정보 수정 (관리자)
    int updatePetByAdmin(PetDto pet);

    // 유저가 가진 모든 펫 삭제
    int deletePetsByUser(@Param("userId") int userId);

    // 펫 삭제 (사용자)
    int deletePetByUser(@Param("petId") int petId,
                        @Param("userId") int userId);

    // 펫 삭제 (관리자)
    int deletePetByAdmin(int petId);

    // 펫 검색
    List<PetDto> searchPets(Map<String, Object> params);

    // 페이징
    List<PetDto> selectPet10(HashMap<String, Object> para);

    // 전체 펫 수
    int selectTotalPetCnt();

}
