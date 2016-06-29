package me.roryclaasen.cavegen;

public class CaveConfig {

	private int minRoomSize = 4, maxRoomSize = 15;
	private int minRooms = 3, maxRooms = 7;
	private int tunnelSize = 1;

	public int getMinRoomSize() {
		return minRoomSize;
	}

	public void setMinRoomSize(int minRoomSize) {
		this.minRoomSize = minRoomSize;
	}

	public int getMaxRoomSize() {
		return maxRoomSize;
	}

	public void setMaxRoomSize(int maxRoomSize) {
		this.maxRoomSize = maxRoomSize;
	}

	public int getMinRooms() {
		return minRooms;
	}

	public void setMinRooms(int minRooms) {
		this.minRooms = minRooms;
	}

	public int getMaxRooms() {
		return maxRooms;
	}

	public void setMaxRooms(int maxRooms) {
		this.maxRooms = maxRooms;
	}

	public int getTunnelSize() {
		return tunnelSize;
	}

	public void setTunnelSize(int tunnelSize) {
		this.tunnelSize = tunnelSize;
	}
}
