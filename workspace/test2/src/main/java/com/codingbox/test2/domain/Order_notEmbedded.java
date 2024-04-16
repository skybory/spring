package com.codingbox.test2.domain;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="ORDERS_NOTEMBEDDED")
public class Order_notEmbedded {

	@Id
	@GeneratedValue
	@Column(name="ORDER_ID")
	private long id;
	
	private LocalDateTime OrderDate;
	private String status;
	
	@JoinColumn(name = "MEMBER_ID")
	@ManyToOne
	private Member_notEmbedded  member;

	
	
	public void setId(long id) {
		this.id = id;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		OrderDate = orderDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void changeMember(Member_notEmbedded member) {
		this.member = member;
		member.getOrders().add(this);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", OrderDate=" + OrderDate + ", status=" + status + "]";
	}
	
	
	
}


