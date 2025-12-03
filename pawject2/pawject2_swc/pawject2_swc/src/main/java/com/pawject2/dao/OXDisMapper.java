package com.pawject2.dao;

import java.util.List;
import java.util.Map;

import com.pawject2.dto.Disease_ox;

@OXDisDao
public interface OXDisMapper {
   public List<Disease_ox> selectAll(Map<String, Integer> params);
   public  Disease_ox select(Disease_ox dto);
   public  int insert(Disease_ox dto);
   public  int update(Disease_ox dto);
   public  int delete(Disease_ox dto);

}
