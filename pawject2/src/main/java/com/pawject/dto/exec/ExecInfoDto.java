package com.pawject.dto.exec;

import lombok.Data;

@Data
public class ExecInfoDto {
	private int execid;
	private String exectype;
	private String description;
	private float avgkcal30min;
	private int exectargetmin;
	private String suitablefor;
	private String intensitylevel;
}
