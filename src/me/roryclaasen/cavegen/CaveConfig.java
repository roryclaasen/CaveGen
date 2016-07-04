package me.roryclaasen.cavegen;

public class CaveConfig {

	private boolean debugOutput = false;

	private double caveRange = 0.2;
	private double featureSize = 24.0;

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

	public boolean doDebugOutput() {
		return debugOutput;
	}

	public void setDebugOutput(boolean debugOutput) {
		this.debugOutput = debugOutput;
	}
}
