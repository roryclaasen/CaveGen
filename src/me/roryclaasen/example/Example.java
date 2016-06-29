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

	public Example() {
		init();

		CaveConfig config = new CaveConfig();
		String seed = JOptionPane.showInputDialog(frame, "Enter a seed or press cancel for a random seed");
		try {
			long seedL = Long.parseLong(seed);
			caveGen = new CaveGenerator(100, 100, seedL);
		} catch (Exception e) {
			caveGen = new CaveGenerator(100, 100);
		}
		caveGen.generate(config);
		panel.repaint();
	}

	@SuppressWarnings("serial")
	private void init() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(800, 600));
		frame.pack();
		// frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Cave Gen Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.clearRect(0, 0, this.getWidth(), this.getHeight());
				int size = 8;
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
						g.fillRect(x * size, y * size, size, size);
					}
				}
			}
		};
		frame.add(panel);
	}

	public void show() {
		frame.setVisible(true);
	}
}
