package com.kata.bowling;

public class BowlingScoreCalculator {
    private Frame[] frames;
    
    public int calculateScore(Frame[] frames) {
    	this.frames = frames;
    	int score = 0;
    	for (int i = 0; i < frames.length; i++) {
			if (frames[i].isStrike && !frames[i].is10thFrame) {
				score+=calculateStrike(i);
			} else if (frames[i].isSpare && !frames[i].is10thFrame) {
				score+=calculateSpare(i);
			} else if (frames[i].is10thFrame){
				score+=frames[i].pin1 + frames[i].pin2 + frames[i].pin3;
			} else {
				score+=frames[i].pin1 + frames[i].pin2;
			}
    	}
    	return score;
    }
    //a strike total is 10 + your next 2 pins
    private int calculateStrike(int index) {
    	try {
        	int score = 0;
        	if(!frames[index+1].isStrike || frames[index+1].is10thFrame) {
        		score = 10 + frames[index+1].pin1 + frames[index+1].pin2;
        	} else if(index <= 7 && frames[index+1].isStrike) {
        		score = 10 + frames[index+1].pin1 + frames[index+2].pin1;
        	} 
        	return score;
    	} catch (IndexOutOfBoundsException e) {
    		throw e;
    	}
    }
    //a spare total is 10 + your next pin
    private int calculateSpare(int index) {
		return 10 + frames[index+1].pin1;
    }

}
