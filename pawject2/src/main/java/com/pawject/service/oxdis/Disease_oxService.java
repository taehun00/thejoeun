package com.pawject.service.oxdis;

import java.util.List;
import java.util.Map;

import com.pawject.dto.oxdis.Disease_ox;

public interface Disease_oxService {
	
	   public List<Disease_ox> selectAll(Map<String, Integer> params);
	   public  Disease_ox select(Disease_ox dto);
	   public  int insert(Disease_ox dto);
	   public  int update(Disease_ox dto);
	   public  int delete(Disease_ox dto);

}
