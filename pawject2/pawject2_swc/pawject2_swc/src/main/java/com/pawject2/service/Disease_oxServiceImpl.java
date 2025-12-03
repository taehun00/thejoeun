package com.pawject2.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject2.dao.OXDisDao;
import com.pawject2.dto.Disease_ox;

@Service
public class Disease_oxServiceImpl implements Disease_oxService {
	@Autowired OXDisDao     dao;
	public int insert(Disease_ox dto) {
		try {dto.setBip ()
	}

	@Override public List<Disease_ox> selectAll(Map<String, Integer> params) {  return null; }

	@Override public Disease_ox select(Disease_ox dto) {  return null; }

	@Override public int insert(Disease_ox dto) {  return 0; }

	@Override public int update(Disease_ox dto) {  return 0; }

	@Override public int delete(Disease_ox dto) {  return 0;}

}
