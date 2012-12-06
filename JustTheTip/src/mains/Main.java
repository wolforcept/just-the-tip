package mains;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main();
	}

	JFrame frame;
	Panel panel;

	int x;

	int herSpeed = 7;
	int hisSpeed = 2;

	BufferedImage him, her;

	public Main() throws IOException {

		him = ImageIO.read(getClass().getResource("/resources/him.gif"));
		her = ImageIO.read(getClass().getResource("/resources/her2.gif"));

		int mode = JOptionPane.showConfirmDialog(null,
				"Wanna play on hard mode?", "Welcome to Just-the-Tip",
				JOptionPane.YES_NO_OPTION);
		System.out.println("mode: " + mode);
		if (0 == mode) {

			herSpeed = 4;

		}
		frame = new JFrame("Just the Tip");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(640, 640);
		frame.setLocationRelativeTo(null);

		panel = new Panel();
		frame.add(panel);

		x = 400;

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				moveHer();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
			}
		});

		new Drawer().start();

		frame.setVisible(true);
	}

	private void moveHer() {
		if (x > -50)
			x -= herSpeed;
	}

	private void update() {

		if (x < 400)
			x += hisSpeed;

	}

	class Drawer extends Thread {

		@Override
		public void run() {
			try {
				while (true) {
					update();
					panel.repaint();
					sleep(1000 / 60);
				}
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
			}
		}
	}

	class Panel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			g.clearRect(0, 0, 1000, 1000);
			g.drawImage(him, -100, 100, null);
			g.drawImage(her, x, -200, null);
		}

	}
}
