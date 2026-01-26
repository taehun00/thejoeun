package com.pawject.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 운동SNS 게시글 엔티티
 */

@Entity
@Getter @Setter
@Table(name = "WALKINGCOURSE")
public class CourseSearch {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "walkingcourse_seq")  //시퀀스 사용
	@SequenceGenerator(name = "walkingcourse_seq", sequenceName = "WALKINGCOURSE_SEQ" , allocationSize = 1)    
    @Column(name = "COURSEID")
    private Long courseid;

    @Column(name = "LOCATION", nullable = false, length = 100)
    private String location;
    
    @Column(name = "LAT")
    private int lat;
    
    @Column(name = "LNG")
    private int lng;
    
	@Column(nullable = false , name="CREATEDAT")
	private LocalDateTime createdAt; // 생성일시

	@PrePersist
	void onCreate() {
		this.createdAt = LocalDateTime.now();
	}


	// 테스트생성자
	public CourseSearch(String location) {
		super();
		this.location = location;
	}
	
	
}
