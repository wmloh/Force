package Force_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Random;


import javax.swing.JPanel;
import javax.swing.Timer;


public class graphics extends JPanel implements KeyListener, ActionListener, MouseListener {

	private Timer timer;
	
	private int delay = 8;
	private boolean play = false;
	private double elas = 0.9;
	private double gravity = 0.1;
	
	private boolean pressed = false;
	private int click_x;
	private int click_y;
	public static int ball_num = 8; 

	Random rand = new Random();
	CreateBalls ball[] = new CreateBalls[ball_num];
	public static long[][] ball_pos = new long[ball_num][2];
	
	{
		for (int i = 0; i < ball_num; i++) {
			CreateBalls temp = new CreateBalls(0,0,rand.nextInt(650), rand.nextInt(550));
			ball[i] = temp;
		}
	}
	
	//CreateBalls ball = new CreateBalls(0,0,rand.nextInt(650), rand.nextInt(550));
	//CreateBalls[] ball = new CreateBalls[] {new CreateBalls(0,0,300, 100), new CreateBalls(0,0,400,200)};
	
	public graphics() {
		
		addKeyListener(this);
		
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		// background
		g.setColor(Color.white);
		g.fillRect(0, 0, 700, 600);
		
		//ball
		for (int i = 0; i < ball_num; i++) {
			ball[i].draw((Graphics2D)g);
		}
		
		
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		play = true;
		
		//click_x = e.getX();
		//click_y = e.getY();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressed = true;
		
		click_x = e.getX();
		click_y = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		while(pressed) {
			pressed = false;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) {
			
			for (int i = 0; i < ball_num; i++) {
				double speed_x = ball[i].getSpeed()[0];
				double speed_y = ball[i].getSpeed()[1] + gravity;
				
				long pos_x = ball[i].getPos()[0];
				long pos_y = ball[i].getPos()[1];
				
				ball[i].setSpeed(speed_x, speed_y);
				
				long move_x = Math.round(pos_x + speed_x);
				long move_y = Math.round(pos_y + speed_y);
				
				ball_pos[i][0] = pos_x;
				ball_pos[i][1] = pos_y;
				
				for (int j = 0; j < ball_num && i != j; j++) {
					if (Math.abs(ball_pos[j][0]- pos_x) <= 15 && Math.abs(ball_pos[j][1] - pos_y) <= 15) {
						ball[i].setSpeed(speed_x = -elas * speed_x, speed_y = -elas * speed_y);
						move_x = Math.round(pos_x + speed_x);
						move_y = Math.round(pos_y + speed_y);
						ball[j].setSpeed(-elas * ball[j].getSpeed()[0], -elas * ball[j].getSpeed()[1]);
						long j_move_x = Math.round(ball[j].getPos()[0] + -elas * ball[j].getSpeed()[0]);
						long j_move_y = Math.round(ball[j].getPos()[1] + -elas * ball[j].getSpeed()[1]);		
						ball[j].setPos(j_move_x, j_move_y);
					}
				}
				
				
				
				if ((20 <= move_x) && (move_x <= 680) && (20 <= move_y) && (move_y <= 580)) {
					
					ball[i].setPos(move_x, move_y);
					
					
				} else if (570 <= move_y) {
					ball[i].setSpeed(speed_x, -elas * speed_y);
				} else if (move_x <= 20) {
					ball[i].setSpeed(-elas * speed_x, speed_y);
				} else if (move_x >= 650) {
					ball[i].setSpeed(-elas * speed_x, speed_y);
				}
				
				if (pressed) {
					
					double dx = pos_x - click_x;
					double dy = pos_y - click_y;
					
					double dist = Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2));
					
					double increment = 0.3 * dist * Math.pow(1.05, -dist*0.6);
					double abs_angle = Math.abs(Math.atan(dy/dx));
					
					double angle;
					
					if(dx > 0 && dy > 0) {
						angle = abs_angle;
					} else if (dx < 0 && dy > 0) {
						angle = Math.PI - abs_angle;
					} else if (dx < 0 && dy < 0) {
						angle = Math.PI + abs_angle;
					} else {
						angle = 2 * Math.PI - abs_angle; 
					}
					
					System.out.println("angle: " + angle);
					System.out.println("increment: " + increment);
					ball[i].addSpeed(Math.cos(angle) * -increment, Math.sin(angle) * -increment);
					System.out.println("speed_x: " + Math.cos(angle) * increment);
					System.out.println("speed_y: " + Math.sin(angle) * increment);
				}
			}
			
			
		}
		
		repaint();
		
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
