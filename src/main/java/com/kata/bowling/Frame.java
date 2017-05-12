package com.kata.bowling;

public class Frame {
	
	public int pin1;
	public int pin2;
	public int pin3;
	public boolean isSpare;
	public boolean isStrike;
	public boolean is10thFrame;
	
	public Frame(int pin1, int pin2, int pin3) {
		isStrike = false;
		isSpare = false;
		is10thFrame = (pin3 >= 0);
		
		this.pin1 = pin1;
		this.pin2 = pin2;
		this.pin3 = pin3; // pin3 will be set to -1 if it is not the 10th frame
		if(pin1==10) {
			isStrike = true;
		}
		if(pin1+pin2 == 10) {
			isSpare = true;
		}
		
	}
	
	
}
