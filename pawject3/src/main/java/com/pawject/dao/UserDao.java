package com.pawject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.AuthDto;
import com.pawject.dto.UserAuthDto;
import com.pawject.dto.UserDto;

@Mapper
public interface UserDao {
	/* 회원관리 */
	public int insertUser(UserDto dto);
	public UserAuthDto readAuthByEmail(UserDto dto);
	public UserDto findByEmail(UserDto dto);
	public int iddoubleByEmail(UserDto dto);
	public int updateUser(UserDto dto);
	public int deleteUser(UserDto dto);
	
	/* 권한관리 */
	public int insertAuth(AuthDto dto);
	public int deleteAuth(AuthDto dto);
}
