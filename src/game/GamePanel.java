package game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;

	// Render
	private Graphics2D g2d;
	private BufferedImage image;

	// The game loop
	// do a tutorial on this.
	private Thread thread;
	private boolean running;
	private long targetTime;

	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);

	}

	@Override
	public void addNotify() {
		super.addNotify();
		thread = new Thread(this);
		thread.start();
	}

	// frame per second
	// must be for different levels.
	// check over this
	private void setFPS(int fps) {
		targetTime = 1000 / fps;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (running)
			return;
		init();
		long startTime;
		long elapsed;
		long wait;
		while (running) {

			update();
			requestRender();

			startTime = System.nanoTime();
			elapsed = System.nanoTime() - startTime;
			wait = targetTime - (elapsed / 1000000);
			if (wait > 0) {
				try {
					Thread.sleep(wait);
				} catch (Exception e) {
					// determine what to do.
					// improve this leave for now.
				}
			}
		}
	}

	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		
		running = true;
		setFPS(10);

	}

	private void requestRender() {
		// TODO Auto-generated method stub

	}

	private void update() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics2D g2d) {

	}

}
