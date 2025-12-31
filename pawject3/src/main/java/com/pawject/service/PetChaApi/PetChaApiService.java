package com.pawject.service.PetChaApi;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.PetCharacter.PetCharacterDao;
import com.pawject.dto.PetCharacter.PetCharacter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetChaApiService {

    private final PetCharacterDao petCharacterDao;
    private final OpenAiImageClient openAiImageClient;

    public String createCharacter(Integer userid, MultipartFile file, String kindpet) {
        String prompt = (file != null && !file.isEmpty())
                ? "귀여운 반려동물 캐릭터로 변환"
                : "귀여운 " + kindpet + " 캐릭터 일러스트";

        String imageUrl = openAiImageClient.call(file, prompt);

        // DB 저장
        PetCharacter character = new PetCharacter();
        character.setUserid(userid);
        character.setKindpet(kindpet);
        character.setPrompt(prompt);
        character.setImageurl(imageUrl);

        petCharacterDao.insertCharacter(character);

        return imageUrl;
    }
}