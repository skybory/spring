package com.codingbox.jpaitem.embedded;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
@Embeddable
@Getter @Setter
public class Period {

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	// 파라미터가 있는 생성자 만들기
	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Period() {
		super();
	}
	
	
	
}
