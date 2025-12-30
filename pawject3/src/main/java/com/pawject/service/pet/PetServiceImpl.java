package com.pawject.service.pet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.PetDao;
import com.pawject.dto.pet.PetDto;
import com.pawject.util.UtilUpload;

@Service
public class PetServiceImpl implements PetService {
	
    @Autowired private final PetDao pdao;
    @Autowired private UtilUpload utilUpload;
    
    // 생성자 주입 (Spring Boot 권장 방식)
    public PetServiceImpl(PetDao pdao) {
        this.pdao = pdao;
    }

    /* 파일 업로드 공통 처리 */
    public String uploadFile(MultipartFile file, String existingFile) {
        if (file != null && !file.isEmpty()) {
            try {
                return utilUpload.fileUpload(file); // UUID_원본파일명 형태로 저장
            } catch (IOException e) {
                throw new RuntimeException("파일 업로드 실패", e);
            }
        }
        // 업로드하지 않았다면 기존 파일명 유지하거나 기본 이미지 사용
        return existingFile != null ? existingFile : "no.png";
    }

    @Transactional
    @Override
    public int petJoin(MultipartFile file, PetDto pet) {
        // 1. 파일 업로드 처리 (없으면 no.png)
        pet.setPFile(uploadFile(file, "no.png"));

        // 2. DB 저장
        return pdao.petJoin(pet);
    }


    @Override
    public List<PetDto> selectPetsByUserId(int userId) {
        return pdao.selectPetsByUserId(userId);
    }

    @Override
    public PetDto selectPetDetail(int userId, int petId) {
        return pdao.selectPetDetail(userId, petId);
    }

    @Transactional
    @Override
    public int updatePetByUser(MultipartFile file, @Param("pet") PetDto pet, int userId) {
        // 파일 업로드 처리 (없으면 기존 파일 유지, 기존도 없으면 no.png)
        String savedFileName = uploadFile(file, pet.getPFile());
        pet.setPFile(savedFileName);

        // DB 업데이트 실행
        return pdao.updatePetByUser(pet, userId);
    }


    @Override
    public PetDto selectPetDetailByAdmin(int petId) {
        return pdao.selectPetDetailByAdmin(petId);
    }

    @Override
    public int updatePetByAdmin(PetDto pet) {
        return pdao.updatePetByAdmin(pet);
    }

    @Override
    public int deletePetByUser(@Param("petId") int petId, @Param("userId") int userId) {
        return pdao.deletePetByUser(petId, userId);
    }

    @Override
    public int deletePetByAdmin(int petId) {
        return pdao.deletePetByAdmin(petId);
    }

    @Override
    public List<PetDto> searchPets(String keyword, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("type", type);
        return pdao.searchPets(params);
    }

    @Override
    public List<PetDto> selectPet10(int pstartno) {
        HashMap<String, Object> para = new HashMap<>();
        int start = (pstartno - 1) * 10 + 1;
        para.put("start", start);
        para.put("end", start + 9);

        return pdao.selectPet10(para);
    }

    @Override
    public int selectTotalPetCnt() {
        return pdao.selectTotalPetCnt();
    }

}
