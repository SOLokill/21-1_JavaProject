package middleProject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMain extends JFrame implements KeyListener {
	static Random rand = new Random();
	Image backIm = new ImageIcon(("images/underwater.jpg")).getImage();
	Image player_fish = new ImageIcon(("images/playerL.png")).getImage();
	Image fish_1 = new ImageIcon(("images/fish1L.png")).getImage();
	Image fish_2 = new ImageIcon(("images/fish2L.png")).getImage();
	Image fish_3 = new ImageIcon(("images/fish6L.png")).getImage();
	Fish playerFish = new Fish(player_fish, 620, 400, 100, 100); // set fish's location and size
	Fish fish1 = new Fish(fish_1, rand.nextInt(1280), rand.nextInt(850), 30, 20);
	Fish fish2 = new Fish(fish_2, rand.nextInt(1280), rand.nextInt(850), 40, 20);
	Fish fish3 = new Fish(fish_3, rand.nextInt(1280), rand.nextInt(850), 100, 50);

	JPanel playerFishPanel = new JPanel();
	ArrayList<Fish> mob = new ArrayList<Fish>();
	ArrayList<Image> list = new ArrayList<Image>();

	public GameMain() {
		mainFrame();
	}

	/**
	 * window frame
	 */
	public void mainFrame() {
		list.add(fish_1);
		list.add(fish_2);
		list.add(fish_3);
		setTitle("상어 키우기");

		setSize(1280, 850);
		setLayout(null); // make free to set layout
		setLocationRelativeTo(null); // set frame location center
		setResizable(false); // can not resizing frame
		setVisible(true); // let frame see
		add(playerFishPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // make sure off the frame
		addKeyListener(this);
		mob.add(fish1);
		mob.add(fish2);
		mob.add(fish3);
	}

	/**
	 * set frame background image
	 */
	public void paint(Graphics g) {
		g.drawImage(backIm, 0, 0, null);
//		super.paintComponents(g); // delete background image (use when check the image is on frame)  
//		g.drawRect(playerFish.getX() + 10, playerFish.getY() + 30, playerFish.getWidth() - 20,
//				playerFish.getHeight() - 50); // crash space
		g.drawImage(player_fish, playerFish.getX(), playerFish.getY(), playerFish.getWidth(), playerFish.getHeight(),
				null);
		for (int i = 0; i < mob.size(); i++) {
			g.drawImage(list.get(i), mob.get(i).getX(), mob.get(i).getY(), mob.get(i).getWidth(),
					mob.get(i).getHeight(), null);
		}
	}

	/**
	 * main method
	 */
	public static void main(String[] agrs) {
		GameMain game = new GameMain();

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * player moving manager
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == e.VK_KP_RIGHT || key == e.VK_RIGHT) {
			if (playerFish.getX() < 1280 - playerFish.getWidth()) {
				playerFish.setX(playerFish.getX() + 50);
				repaint();
			} else {
				playerFish.setX(0);
				repaint();
			}
		} else if (key == e.VK_KP_LEFT || key == e.VK_LEFT) {
			if (playerFish.getX() > 0) {
				playerFish.setX(playerFish.getX() - 50);
				repaint();
			} else {
				playerFish.setX(1280 - playerFish.getWidth());
				repaint();
			}
		} else if (key == e.VK_KP_UP || key == e.VK_UP) {
			if (playerFish.getY() > 0) {
				playerFish.setY(playerFish.getY() - 50);
				repaint();
			}
		} else if (key == e.VK_KP_DOWN || key == e.VK_DOWN) {
			if (playerFish.getY() < 850 - playerFish.getHeight()) {
				playerFish.setY(playerFish.getY() + 50);
				repaint();
			}
		}
		CollisionCheck();
	}

	/**
	 * let mob fishs moving and check collision
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < mob.size(); i++) {
			if ((i % 2) == 1) {
				if (mob.get(i).getX() > 0) {
					mob.get(i).setX(mob.get(i).getX() - 10);
					repaint();
				} else {
					mob.get(i).setX(1280 - mob.get(i).getWidth());
					repaint();
				}
			} else {
				if (mob.get(i).getX() < 1280 - mob.get(i).getWidth()) {
					mob.get(i).setX(mob.get(i).getX() + 10);
					repaint();
				} else {
					mob.get(i).setX(0);
					repaint();
				}
			}
		}
		CollisionCheck();
	}

	/**
	 * make random fishs
	 * 
	 * @return fish object
	 */
	public Fish makeRandFish() {
		if (playerFish.getX() >= 600) {
			Fish fish = new Fish(list.get(rand.nextInt(list.size())), rand.nextInt(20), rand.nextInt(850),
					playerFish.getWidth() - 60, 60);
			return fish;
		} else {
			Fish fish = new Fish(list.get(rand.nextInt(list.size())), rand.nextInt(1200), rand.nextInt(850),
					playerFish.getWidth() - 60, 60);
			return fish;
		}
	}

	public void CollisionCheck() {
		for (int i = 0; i < mob.size(); i++) {
			if (Collision(i) == true) {
				if (mob.get(i).getSize() < playerFish.getSize()) {
					mob.remove(i);
					mob.add(makeRandFish());
					playerFish.setWidth(playerFish.getWidth() + 30);
					playerFish.setHeight(playerFish.getHeight() + 10);
					break;
				} else {
					System.exit(0);
				}
			} else {
				if (i >= mob.size()) {
					i = 0;
				}
			}
		}
	}

	public boolean Collision(int i) {
		if ((playerFish.getY() < mob.get(i).getY() + mob.get(i).getHeight()
				&& mob.get(i).getY() < playerFish.getY() + playerFish.getHeight())
				&& (playerFish.getX() < mob.get(i).getX() + mob.get(i).getWidth()
						&& mob.get(i).getX() < playerFish.getX() + playerFish.getWidth())) {
			return true;
		}
		return false;
	}
}
