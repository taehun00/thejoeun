package com.pawject.dto.pet;

import com.pawject.domain.Pet;
import com.pawject.domain.PetType;
import com.pawject.domain.User;
import com.pawject.dto.user.UserRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetRequestDto {
    private String petName;     // 펫 이름
    private String petBreed;    // 품종
    private String birthDate;   // 생일 (문자열로 저장됨)
    private Long petTypeId;     // 펫 타입 ID (필수)
    private String pFile;       // 프로필 이미지 파일 경로
    private Integer page;       // 나이
    private String pGender;     // 성별
    
    // RequestDto → Entity 변환
    public Pet toEntity(User user, PetType petType) {
        return Pet.builder()
                .user(user)
                .petName(petName)
                .petBreed(petBreed)
                .birthDate(birthDate)
                .petType(petType)
                .pfile(pFile)
                .page(page)
                .pGender(pGender)
                .build();
    }


}
