package com.kata.bowling;

import org.junit.*;


public class BowlingGameTest {
	private static ScoreParser parser;
	private static BowlingScoreCalculator calculator;
	private static int score;

	@BeforeClass
    public static void runOnceBeforeClass() {
		parser = new ScoreParser();
		calculator = new BowlingScoreCalculator();
		score = 0;
    }
    @Test
    public void testPerfectGame() {
    	parser.populateFrames("XXXXXXXXXXXX");
    	score = calculator.calculateScore(parser.getFrames());
    	Assert.assertEquals(score, 300);
    }
    @Test(expected=IllegalArgumentException.class)
    public void testGameThatHasTooManyFrames() {
    
    	//game with too many frames..
    	parser.populateFrames("XXXXXXXXXXXXXXX");
    	calculator.calculateScore(parser.getFrames());
    }
    
    @Test
    public void testIncompleteGame() {
    	parser.populateFrames("XXXX");
    	Assert.assertFalse(parser.isCompleteGame());
    }

    
    @Test(expected=IllegalArgumentException.class)
    public void testInvalidChars() {
    	parser.populateFrames("tonyc ftw");
    	calculator.calculateScore(parser.getFrames());
    }
    
    @Test
    public void testAllSpares() {
    	parser.populateFrames("9/9/9/9/9/9/9/9/9/9/9");
    	score = calculator.calculateScore(parser.getFrames());
    	Assert.assertTrue(parser.isCompleteGame());
    	Assert.assertEquals(score, 190);
    }

    @Test
    public void testGames() {
    	parser.populateFrames("5/4/5472XX9/279/XXX");
    	score = calculator.calculateScore(parser.getFrames());
    	Assert.assertTrue(parser.isCompleteGame());
    	Assert.assertEquals(score, 167);

    	
    	parser.populateFrames("X818/-9XXX9-9/81");
    	score = calculator.calculateScore(parser.getFrames());
    	Assert.assertTrue(parser.isCompleteGame());
    	Assert.assertEquals(score, 161);

    	parser.populateFrames("X818/-9XXX9-9/81");
    	score = calculator.calculateScore(parser.getFrames());
    	Assert.assertTrue(parser.isCompleteGame());
    	Assert.assertEquals(score, 161);

    	parser.populateFrames("X818/-94532139-9/81");
    	score = calculator.calculateScore(parser.getFrames());
    	Assert.assertTrue(parser.isCompleteGame());
    	Assert.assertEquals(score, 101);

    	parser.populateFrames("X818/-94532139-9/8-");
    	score = calculator.calculateScore(parser.getFrames());
    	Assert.assertTrue(parser.isCompleteGame());
    	Assert.assertEquals(score, 100);
    
    }


}
