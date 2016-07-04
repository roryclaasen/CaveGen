package me.roryclaasen.cavegen;

import java.util.Random;

public class CaveGenerator {

	private long seed;
	private CaveLevel level;

	public CaveGenerator(int width, int height) {
		this(width, height, new Random().nextLong());
	}

	public CaveGenerator(int width, int height, long seed) {
		this.seed = seed;
		init(width, height);
	}

	private void init(int width, int height) {
		level = new CaveLevel(width, height, seed);
	}

	public void generate() {
		level.generate(new CaveConfig());
	}

	public void generate(CaveConfig config) {
		level.generate(config);
	}

	public CaveLevel getLevel() {
		return level;
	}

	public long getSeed() {
		return seed;
	}
}
