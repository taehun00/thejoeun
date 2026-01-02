package com.pawject.dto.PetCharacter;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetCharacter {
	private Integer charid;
    private Integer userid;
    private String kindpet;
    private String prompt;
    private String imageurl;
    private Date createdat;
}