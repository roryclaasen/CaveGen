package me.roryclaasen.cavegen;

import me.roryclaasen.cavegen.noise.OpenSimplexNoise;

public class CaveLevel {

	private final int WIDTH, HEIGHT;
	private final long SEED;

	private OpenSimplexNoise noise;

	private int[] tiles;

	protected CaveLevel(int width, int height, long seed) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.SEED = seed;

		this.tiles = new int[WIDTH * HEIGHT];
	}

	protected void generate(CaveConfig config) {
		if (config.doDebugOutput()) System.out.println("Creating level with seed: " + this.SEED);
		noise = new OpenSimplexNoise(this.SEED);
		if (config.doDebugOutput()) System.out.println("Setting tiles");
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				double value = noise.eval(x / config.getFeatureSize(), y / config.getFeatureSize());
				if (value > -config.getCaveRange() && value < config.getCaveRange()) tiles[x + y * WIDTH] = 1;
			}
		}
		if (config.doDebugOutput()) System.out.println("Done!");
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getTile(int x, int y) {
		if (x < 0 || x >= WIDTH) throw new IndexOutOfBoundsException("Index " + x + " is out of bounds!");
		if (y < 0 || y >= HEIGHT) throw new IndexOutOfBoundsException("Index " + y + " is out of bounds!");
		return tiles[x + y * WIDTH];
	}
}
