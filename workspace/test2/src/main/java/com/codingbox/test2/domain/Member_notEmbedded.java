package com.codingbox.test2.domain;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member_notEmbedded {

	@Id
	@GeneratedValue
	@Column(name="MEMBER_ID")
	private long id;


	private String name;
	
	private String city;
	private String street;
	private String zipcode;

	
	@OneToMany(mappedBy = "member")
	private List<Order_notEmbedded> orders = new ArrayList<>();


	public void addOrder(Order_notEmbedded order) {
		this.orders.add(order);
	}
	
	@Override
	public String toString() {
		return "Member_notEmbedded [id=" + id + ", name=" + name + ", city=" + city + ", street=" + street
				+ ", zipcode=" + zipcode + "]";
	}


	
}
