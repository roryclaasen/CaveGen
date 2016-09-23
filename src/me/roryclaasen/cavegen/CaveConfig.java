package me.roryclaasen.cavegen;

public class CaveConfig {

	private boolean debugOutput = false;

	private double caveRange = 0.2;
	private double featureSize = 24.0;
	private int cavernSize = 15;
	private int cavernLoop = (cavernSize / 3) + 2;

	public double getCaveRange() {
		return caveRange;
	}

	public void setCaveRange(double caveRange) {
		this.caveRange = caveRange;
	}

	public double getFeatureSize() {
		return featureSize;
	}

	public void setFeatureSize(double featureSize) {
		this.featureSize = featureSize;
	}

	public int getCavenSize() {
		return cavernSize;
	}

	public void setCavenSize(int cavenSize) {
		this.cavernSize = cavenSize;
	}

	public int getCavernLoop() {
		return cavernLoop;
	}

	public void setCavernLoop(int cavernLoop) {
		this.cavernLoop = cavernLoop;
	}

	public boolean doDebugOutput() {
		return debugOutput;
	}

	public void setDebugOutput(boolean debugOutput) {
		this.debugOutput = debugOutput;
	}
}
