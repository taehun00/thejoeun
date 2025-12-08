package com.pawject.dao.food;

import java.util.List;

import com.pawject.dao.MyDao;
import com.pawject.dto.food.NutriDto;

@MyDao
public interface NutriDao {
	
	//<insert id="nutriinsert" parameterType="NutriDto">
	public int nutriinsert(NutriDto dto);
	
	//<select resultType="NutriDto" id="nutriselectAll">
	public List<NutriDto> nutriselectAll();
	
	//<select resultType="NutriDto" id="nutriselect" parameterType="int">
	public List<NutriDto> nutriselect(int foodid);
	
	//<update id="nutriupdate" parameterType="NutriDto">
	public int nutriupdate(NutriDto dto);
	
	//<delete id="nutridelete" parameterType="NutriDto">
	public int nutridelete(NutriDto dto);
	
	//<delete id="nutrideleteAll" parameterType="int">
	public int nutrideleteAll(int foodid);
	
//	<select resultMap="NutriMap" id="nutriselectForWrite" parameterType="int">
	public NutriDto nutriselectForWrite(int foodid);
	
	//	<select id="nutrientSelectName" resultType="NutriDto">
	public List<NutriDto> nutrientSelectName();
	
	//	<select resultMap="NutriMap" id="nutriselectWithInfo" parameterType="int">
	public List<NutriDto> nutriselectWithInfo(int foodid);

}
