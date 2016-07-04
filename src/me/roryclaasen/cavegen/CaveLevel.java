package me.roryclaasen.cavegen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CaveLevel {

	private final int WIDTH, HEIGHT;
	private final Random RANDOM;

	private int[] tiles;
	private CaveConfig config;

	protected CaveLevel(int width, int height, Random random) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.RANDOM = random;

		this.tiles = new int[WIDTH * HEIGHT];
	}

	protected void generate(CaveConfig config) {
		this.config = config;
		if (config.doDebugOutput()) System.out.println("Setting tiles");
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
