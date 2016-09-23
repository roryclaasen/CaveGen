package me.roryclaasen.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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

	private int drawSize = 3;
	private int cWidth = 200, cHeight = 200;

	private boolean drawGrid = false;

	private int mID, mX, mY;

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
				for (int x = 0; x < caveGen.getWidth(); x++) {
					for (int y = 0; y < caveGen.getHeight(); y++) {
						try {
							int id = caveGen.getTile(x, y);
							g.setColor(Color.DARK_GRAY);
							if (id == 1) g.setColor(Color.PINK);
							if (id == 2) g.setColor(Color.GREEN); // id 2 is being used when testing a new tile
							g.fillRect(x * drawSize, y * drawSize, drawSize, drawSize);
							if (drawGrid) {
								g.setColor(Color.BLACK);
								g.drawLine(0, y * drawSize, getHeight(), y * drawSize);
							}
						} catch (Exception e) {
							// e.printStackTrace();
							System.out.println(e.getLocalizedMessage());
						}
					}
					if (drawGrid) {
						g.drawLine(x * drawSize, 0, x * drawSize, getWidth());
						g.setColor(Color.BLACK);
					}
				}
				g.setColor(Color.WHITE);
				g.drawString(caveGen.getSeed() + "", 5, 15);

				g.drawString(mID + " (" + mX + "," + mY + ")", mX * drawSize, mY * drawSize);
				g.setColor(new Color(255, 255, 255, 150));
				g.fillRect(mX * drawSize, mY * drawSize, drawSize, drawSize);
			}
		};
		panel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				update(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				update(e);
			}

			private void update(MouseEvent e) {
				mX = e.getX() / drawSize;
				mY = e.getY() / drawSize;
				mID = caveGen.getTile(mX, mY);
				panel.repaint();
			}
		});
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_G) drawGrid = !drawGrid;
				panel.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_G) drawGrid = !drawGrid;
			}
		});
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
		panel.requestFocus();
	}
}
