package com.pawject.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto {
	private String email;
    private String password;
    private List<AuthDto> authList;
}
