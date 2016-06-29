package me.roryclaasen.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import me.roryclaasen.cavegen.CaveConfig;
import me.roryclaasen.cavegen.CaveGenerator;

public class Example {

	public static void main(String[] args) {
		Example example = new Example();
		example.show();
	}

	private JFrame frame;
	private JPanel panel;
	private CaveGenerator caveGen;

	private int drawSize = 8;
	private int cWidth = 100, cHeight = 100;

	public Example() {
		init();

		CaveConfig config = new CaveConfig();
		config.setDebugOutput(true);
		String seed = JOptionPane.showInputDialog(frame, "Enter a seed or press cancel for a random seed");
		try {
			long seedL = Long.parseLong(seed);
			caveGen = new CaveGenerator(cWidth, cHeight, seedL);
		} catch (Exception e) {
			caveGen = new CaveGenerator(cWidth, cHeight);
		}
		caveGen.generate(config);
		panel.repaint();
	}

	@SuppressWarnings("serial")
	private void init() {
		frame = new JFrame();
		panel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.clearRect(0, 0, this.getWidth(), this.getHeight());
				for (int x = 0; x < caveGen.getLevel().getWidth(); x++) {
					for (int y = 0; y < caveGen.getLevel().getHeight(); y++) {
						int id = caveGen.getLevel().getTile(x, y);
						switch (id) {
							case (1) :
								g.setColor(Color.PINK);
								break;
							case (2) :
								g.setColor(Color.GREEN);
								break;
							default :
								g.setColor(Color.DARK_GRAY);
								break;
						}
						g.fillRect(x * drawSize, y * drawSize, drawSize, drawSize);
					}
				}
				g.setColor(Color.BLACK);
				g.drawString(caveGen.getSeed() + "", 5, 15);
			}
		};
		panel.setPreferredSize(new Dimension(cWidth * drawSize, cHeight * drawSize));
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setTitle("Cave Gen Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void show() {
		frame.setVisible(true);
	}
}
