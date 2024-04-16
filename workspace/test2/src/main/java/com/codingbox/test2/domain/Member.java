package com.codingbox.test2.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue
	@Column(name="MEMBER_ID")
	private long id;


	private String name;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}

	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

	
}
