package com.bilgeadam.model;

public class TolkienCharacter {
	
	private int age;
	private String name;
	private Race race;
	
	
	public TolkienCharacter(String name,int age, Race race) {
		super();
		this.age = age;
		this.name = name;
		this.race = race;
	}


	public int getAge() {
		return this.age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Race getRace() {
		return this.race;
	}


	@Override
	public String toString() {
		return "TolkienCharacter [age=" + this.age + ", name=" + this.name + ", race=" + this.race + "]";
	}
	
	
	

}
