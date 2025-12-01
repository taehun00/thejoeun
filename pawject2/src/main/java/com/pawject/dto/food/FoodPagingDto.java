package com.pawject.dto.food;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodPagingDto {
	private int listtotal;
	private int onepagelist;
	private int pagetotal;
	private int bottomlist;
	private int pstartno;
	
	private int current;
	private int start;
	private int end;
	
	public FoodPagingDto(int listtotal, int pstartno) {
		super();
		this.listtotal = listtotal;
			if(listtotal<=0) {listtotal=1;}
		this.onepagelist = 10;
		this.pagetotal = (int)Math.ceil((double)listtotal/onepagelist);
		this.bottomlist = 10;
		this.pstartno = (pstartno-1)*onepagelist+1;
		
		this.current = pstartno;
		this.start = ((current-1)/bottomlist)*bottomlist+1;
		this.end = start+bottomlist-1;
			if(end>pagetotal) {end=pagetotal;}
	}
	
	
	

}
