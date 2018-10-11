package com.example.demo.user.enumcommo;

public enum Sex {

	Felmale("女"),
	Male("男");
	
	private String label;
	
	Sex(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
