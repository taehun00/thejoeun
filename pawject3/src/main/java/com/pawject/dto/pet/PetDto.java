package com.pawject.dto.pet;

import com.pawject.dto.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
	private int petId;          // 반려동물 고유 ID
    private int userId;         // 사용자 ID (FK → USERS.USERID)
    private String petName;     // 반려동물 이름
    private String petBreed;    // 반려동물 종 (예: 페르시안, 푸들)
    private String birthDate;   // 생년월일 (문자열로 저장)
    private int petTypeId;      // 반려동물 종류 ID (FK → PETTYPE.PETTYPEID)
    private String pFile;       // 반려동물 이미지 파일명
    private String createdAt;     // 등록일
    private Integer page;        // 나이
    private String pgender;      // 성별 (M/F)
    private String email;

}
