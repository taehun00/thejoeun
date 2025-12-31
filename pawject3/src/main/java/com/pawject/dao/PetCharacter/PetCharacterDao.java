package com.pawject.dao.PetCharacter;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.PetCharacter.PetCharacter;

@Mapper
public interface PetCharacterDao {
    void insertCharacter(PetCharacter character);
    List<PetCharacter> findByUserId(Long userId);
    void deleteCharacter(Long charid);
}
    