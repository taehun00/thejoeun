package com.pawject.dao.PetCharacter;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pawject.dto.PetCharacter.PetCharacter;

@Mapper
public interface PetCharacterDao {
    void insertCharacter(PetCharacter character);

    List<PetCharacter> findByUserId(@Param("userid") Integer userid);

    void deleteCharacter(@Param("charid") Integer charid);
}
