package me.roryclaasen.cavegen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.roryclaasen.cavegen.objects.CaveObject;
import me.roryclaasen.cavegen.objects.CaveRoom;
import me.roryclaasen.cavegen.objects.CaveTunnel;

public class CaveLevel {

	private final int WIDTH, HEIGHT;
	private final Random RANDOM;

	private int[] tiles;
	private CaveConfig config;

	private List<CaveObject> objects = new ArrayList<CaveObject>();

	protected CaveLevel(int width, int height, Random random) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.RANDOM = random;

		this.tiles = new int[WIDTH * HEIGHT];
	}

	protected void generate(CaveConfig config) {
		this.config = config;
		placeRooms();
		if (config.doDebugOutput()) System.out.println("Setting tiles");
		for (CaveObject object : objects) {
			int id = 0;
			if (object instanceof CaveRoom) id = 1;
			if (object instanceof CaveTunnel) id = 2;
			for (int x = object.getX(); x < object.getX() + object.getWidth(); x++) {
				if (x < 0 || x >= WIDTH) continue;
				for (int y = object.getY(); y < object.getY() + object.getHeight(); y++) {
					if (y < 0 || y >= HEIGHT) continue;
					tiles[x + y * WIDTH] = id;
				}
			}
		}
	}

	private void placeRooms() {
		int numberOfRooms = config.getMinRooms() + RANDOM.nextInt(config.getMaxRooms() - config.getMinRooms());
		List<CaveRoom> rooms = new ArrayList<CaveRoom>();
		int added = 0, skipped = 0;
		for (int i = 0; i < numberOfRooms; i++) {
			int width = config.getMinRoomSize() + RANDOM.nextInt(config.getMaxRoomSize() - config.getMinRoomSize());
			int height = config.getMinRoomSize() + RANDOM.nextInt(config.getMaxRoomSize() - config.getMinRoomSize());
			int x = RANDOM.nextInt(WIDTH - width);
			int y = RANDOM.nextInt(HEIGHT - height);

			CaveRoom room = new CaveRoom(x, y, width, height);
			if (!room.intersects(rooms)) {
				if (rooms.size() > 0) {
					Point last = rooms.get(rooms.size() - 1).getCenter();
					Point next = room.getCenter();
					if (RANDOM.nextInt(2) == 1) {
						addTunnelH(last.x, next.x, last.y);
						addTunnelV(last.y, next.y, next.x);
					} else {
						addTunnelV(last.y, next.y, last.x);
						addTunnelH(last.x, next.x, next.y);
					}
				}
				rooms.add(room);
				added++;
			} else {
				skipped++;
			}
		}
		if (config.doDebugOutput()) System.out.println("Added " + added + " room(s), Skipped " + skipped + " room(s)");
		objects.addAll(rooms);
	}

	private void addTunnelV(int y1, int y2, int x) {
		int y = Math.min(y1, y2);
		int height = Math.max(y1, y2) - Math.min(y1, y2);
		CaveTunnel tunnel = new CaveTunnel(x, y, config.getTunnelSize(), height);
		objects.add(tunnel);
	}

	private void addTunnelH(int x1, int x2, int y) {
		int x = Math.min(x1, x2);
		int width = Math.max(x1, x2) - Math.min(x1, x2);
		CaveTunnel tunnel = new CaveTunnel(x, y, width, config.getTunnelSize());
		objects.add(tunnel);
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
