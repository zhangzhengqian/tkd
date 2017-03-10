package com.entity;

public class Student extends Object{
	
	private String grade;
	
	public String id;
	
	protected String name;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		return grade.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj!=null){
			Student s = (Student)obj;
			if(s.getGrade() == this.grade){
				return true;
			}
		}
		Student other = (Student) obj;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		return true;
	}

	public Student() {
	}

	public void reflect1(){
		 System.out.println("输出方法1");
	}
	
	public void reflect2(String id,String name){
		 System.out.println("id"+id+"name:"+name);
	}
}
