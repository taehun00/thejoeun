package com.pawject.service.PetChaApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PetChaApi {
	
	@Value("${openai.api.key}")
	private String PetChaApiKey;

}
