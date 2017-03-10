package com.entity;

public class Car {
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void buyCar(){
		System.out.println("i want buy a car");
	}

	public Car() {
		super();
		System.out.println("Car Construcor is building");
	}
	
	
}
