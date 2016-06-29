package me.roryclaasen.cavegen;

import java.util.Random;

public class CaveLevel {

	private final int WIDTH, HEIGHT;
	private final Random RANDOM;

	private int[] tiles;

	protected CaveLevel(int width, int height, Random random) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.RANDOM = random;

		this.tiles = new int[WIDTH * HEIGHT];
	}

	protected void generate() {

	}

	public int getTile(int x, int y) {
		if (x < 0 || x >= WIDTH) throw new IndexOutOfBoundsException("Index " + x + " is out of bounds!");
		if (y < 0 || y >= HEIGHT) throw new IndexOutOfBoundsException("Index " + y + " is out of bounds!");
		return tiles[x + y * WIDTH];
	}
}
