package com.kata.bowling;

public class ScoreParser {
	private String scores;
	private final int FRAMES_IN_GAME = 10;
    private final char STRIKE = 'X';
    private final char SPARE = '/';
    private final char EMPTY = '-';
    private Frame[] frames;
	private boolean isCompleteGame;

	public void populateFrames(String scores) {
    	isCompleteGame = false;
		this.scores = scores;
		populateFrames();
	}
	private void populateFrames() throws IllegalArgumentException, IndexOutOfBoundsException {
		try {
			int currFrame = 0;
			frames = new Frame[FRAMES_IN_GAME];
			int firstRoll, secondRoll, thirdRoll;
			for(int i = 0; i < scores.length(); i++) {
				firstRoll = 0;
				secondRoll = 0;
				thirdRoll = -1;
				if(currFrame < 9) {
					if(scores.charAt(i)==STRIKE) {
						firstRoll = 10;
					} else if(scores.charAt(i+1)==SPARE) {
						firstRoll = getRollValue(scores.charAt(i));
						secondRoll = 10 - firstRoll;
						// we have calculated the next pin.  so increment i
						i++;
					} else {
						firstRoll = getRollValue(scores.charAt(i));
						secondRoll = getRollValue(scores.charAt(i+1));
						// we have calculated the next pin.  so increment i
						i++;
					}
				} else {
					//10th frame calculations are more tricky
					firstRoll = getTenthFrameValues(scores.charAt(i));
					
					//check to see if first two pins are a spare
					if(scores.charAt(i+1)==SPARE) {
						secondRoll = 10-firstRoll;
					} else {
						secondRoll = getTenthFrameValues(scores.charAt(i+1));
					}
					
					//if first two rolls are a mark, a third roll is allowed on the 10th frame
					if(firstRoll+secondRoll>=10) {
						//check to see if last two pins are a spare
						if(scores.charAt(i+2)==SPARE) {
							thirdRoll = 10-secondRoll;
						} else {
							thirdRoll = getTenthFrameValues(scores.charAt(i+2));
						}
					} else {
						thirdRoll = 0;
					}
					//increment i enough to break out of loop
					i+=3;
				}
				frames[currFrame] = new Frame(firstRoll, secondRoll, thirdRoll);
				currFrame++;
				if(currFrame==10 && i < scores.length()) {
					throw new IllegalArgumentException("Too many frames in this game");
				}
			}
			if(currFrame == 10) {
				isCompleteGame = true;
			}
		} catch(IllegalArgumentException e) {
			throw e;
		} catch(IndexOutOfBoundsException e) {
			throw e;
		}
	}
	


	public boolean isCompleteGame() {
		return isCompleteGame;
	}
	public Frame[] getFrames() {
		return frames;
	}
	
	private int getRollValue(char roll) {
		
		validateBowlingChar(roll);
		return (roll==EMPTY) ? 0 : Character.getNumericValue(roll);
	}
	
	//validates a valid roll
	private void validateBowlingChar(char roll) throws IllegalArgumentException {
		//45  -
		//47  /
		//0-9 48-59
		//88  X
		int charAsciiValue = (int) roll;
		
		boolean isValid = (charAsciiValue == 45 || (charAsciiValue >= 47 && charAsciiValue <=59) || charAsciiValue == 8);
		if(!isValid) {
			throw new IllegalArgumentException("Invalid character passed into the game");
		}
	}
	
	private int getTenthFrameValues(char roll) {
		if (roll==EMPTY) {
			return 0;
		} else if (roll==STRIKE) {
			return 10;
		} else {
			return getRollValue(roll);
		}
	}

}
