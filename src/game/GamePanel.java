package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

	// Game stuff
	Entity head;
	ArrayList<Entity> snake;
	private final int SIZE = 10;
	
	// movement
	private int dx, dy;
	
	// key input
	private boolean up, down, right, left, start;
	
	
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
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_UP) 
			up = true;
		if(k == KeyEvent.VK_DOWN) 
			down = true;
		if(k == KeyEvent.VK_LEFT) 
			left = true;
		if(k == KeyEvent.VK_RIGHT) 
			right = true;
		if(k == KeyEvent.VK_ENTER) 
			start = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_UP) 
			up = true;
		if(k == KeyEvent.VK_DOWN) 
			down = true;
		if(k == KeyEvent.VK_LEFT) 
			left = true;
		if(k == KeyEvent.VK_RIGHT) 
			right = true;
		if(k == KeyEvent.VK_ENTER) 
			start = true;

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
		g2d = image.createGraphics();
		
		running = true;
		setUpLevel();
		setFPS(10);

	}
	
	private void setUpLevel() {
		snake = new ArrayList<Entity>();
		head = new Entity(SIZE);
		head.setPosition(WIDTH / 2, HEIGHT / 2);
		snake.add(head);
		for(int i = 1; i < 10; i++) {
			Entity e = new Entity(SIZE);
			e.setPosition(head.getX() + (i * SIZE), head.getY());
			snake.add(e);
		}
	}

	private void requestRender() {
		render(g2d);
		Graphics g = getGraphics();
		g.drawImage(image, 0,0, null);
		g.dispose();
	}

	private void update() {
		if(up && dy == 0) {
			dy = -SIZE;
			dx = 0;
		}
		
		if(down && dy == 0) {
			dy = -SIZE;
			dx = 0;
		}
		if(left && dx == 0) {
			dy = -SIZE;
			dx = 0;
		}
		if(right && dx == 0) {
			dy = -SIZE;
			dx = 0;
		}
		for(int i = snake.size() - 1; i>0; i--) {
			//snake.get(i).setPosition(snake.get(index), y);
		}

	}

	public void render(Graphics2D g2d) {
		g2d.clearRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.GREEN);
		
		for(Entity e : snake) {
			e.render(g2d);
		}
	}

}
