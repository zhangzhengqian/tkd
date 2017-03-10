package com.entity;

import java.util.List;

public class Person {
	
	private Car car;
	
	private String name;
	
	private String grade;
	
	private List<Car> cars;
	
	public void buy(){
		car.buyCar();
	}
	
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("my name is bob");
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public void init(){
		System.out.println("bean will init");
	}
	public void destory(){
		System.out.println("bean will destory");
	}
	
	public Person() {
		super();
		System.out.println("Person constructer is building");
	}
	
	
}
