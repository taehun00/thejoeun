package com.pawject.dto.pet;

import lombok.Data;

@Data
public class PetDto {
	private int petId;  
    private int userId;   
    private String petName; 
    private String petBreed;
    private String birthDate; 
    private int petTypeId;  
    private String createdAt;  
    private String pfile;

}
