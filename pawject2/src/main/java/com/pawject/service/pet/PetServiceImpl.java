package com.pawject.service.pet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.pet.PetMapper;
import com.pawject.dto.pet.PetDto;

@Service
public class PetServiceImpl implements PetService {
	@Autowired  PetMapper  pdao;

	@Transactional
	@Override 
	public int petJoin(MultipartFile file, PetDto pet) {
        String fileName = null;

        if ( !file.isEmpty() ) {
            fileName = file.getOriginalFilename();
            String uploadPath = "C:/file/";
            File img = new File(uploadPath + fileName);
            try {
                file.transferTo(img); // 파일 업로드
            } catch (IOException e) {
                e.printStackTrace();
            }
            pet.setPfile(fileName);
        } else {
            // 기본 X표시 이미지 지정
            pet.setPfile("x.png");  // 서버에 x.png 파일을 준비해두세요
        }


        

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
    public int updatePetByUser(MultipartFile file, PetDto pet, int userId) {
        String fileName = null;

        if (file != null && !file.isEmpty()) {
            fileName = file.getOriginalFilename();
            String uploadPath = "C:/file/";
            File img = new File(uploadPath + fileName);
            try {
                file.transferTo(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
            pet.setPfile(fileName);
        }

        

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
    public int deletePetByUser(int petId, int userId) {
        return pdao.deletePetByUser(petId, userId);
    }

    @Override
    public int deletePetByAdmin(int petId) {
        return pdao.deletePetByAdmin(petId);
    }

    @Override
    public List<PetDto> searchPets(String keyword) {
        return pdao.searchPets(keyword);
    }


	@Override
	public List<PetDto> selectPet10(int pstartno) {
		HashMap<String, Object> para = new HashMap();
		int start = (pstartno - 1) * 10 + 1;
		para.put("start", start);
		para.put("end", start + 10 - 1);
		
		return pdao.selectPet10(para);
	}


	@Override
	public int selectTotalPetCnt() {
		
		return pdao.selectTotalPetCnt();
	}
    
    

}
