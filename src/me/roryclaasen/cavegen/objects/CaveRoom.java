package me.roryclaasen.cavegen.objects;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

public class CaveRoom extends CaveObject {

	public CaveRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Point getCenter() {
		return new Point((int) Math.round(getBounds().getCenterX()), (int) Math.round(getBounds().getCenterY()));
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public boolean intersects(CaveRoom room) {
		return getBounds().intersects(room.getBounds());
	}

	public boolean intersects(List<CaveRoom> rooms) {
		for (CaveRoom room : rooms) {
			if (intersects(room)) return true;
		}
		return false;
	}
}
