package me.roryclaasen.cavegen;

import java.util.Random;

public class CaveGenerator {

	private Random random;

	private CaveLevel level;

	public CaveGenerator(int width, int height) {
		random = new Random();
		init(width, height);
	}

	public CaveGenerator(int width, int height, long seed) {
		random = new Random(seed);
		init(width, height);
	}

	private void init(int width, int height) {
		level = new CaveLevel(width, height, random);
	}
	
	public void generate(){
		level.generate();
	}

	public CaveLevel getLevel() {
		return level;
	}
}
