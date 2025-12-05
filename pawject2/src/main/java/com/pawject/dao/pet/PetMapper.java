package com.pawject.dao.pet;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pawject.dao.MyDao;
import com.pawject.dto.pet.PetDto;

@MyDao
public interface PetMapper {
	public int petJoin(PetDto pet);
    public List<PetDto> selectPetsByUserId(int userId);
    public PetDto selectPetDetail(@Param("userId") int userId,
            @Param("petId") int petId);
    
    // 펫 정보 수정(사용자)
    public int updatePetByUser(@Param("pet") PetDto pet, @Param("userId") int userId);

    // 펫 정보 수정(관리자)
    public int updatePetByAdmin(PetDto pet);
    
    // 유저가 가진 모든 펫 삭제
    int deletePetsByUser(@Param("userId") int userId);

    
    // 펫 삭제(사용자)
    public int deletePetByUser(@Param("petId") int petId, @Param("userId") int userId);

    // 펫 삭제(관리자)
    public int deletePetByAdmin(int petId);

    
    // 검색
    public List<PetDto> searchPets(String keyword);



}
