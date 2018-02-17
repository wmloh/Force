package Force_package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class CreateBalls {
	
	private double speed_x;
	private double speed_y;
	private long pos_x;
	private long pos_y;
	
	public CreateBalls(double s_x, double s_y, long x, long y) {
		speed_x = s_x;
		speed_y = s_y;
		pos_x = x;
		pos_y = y;
	}
	
	public void setSpeed(double x, double y) {
		speed_x = x;
		speed_y = y;
	}
	
	public double[] getSpeed() {
		double[] output = {speed_x, speed_y};
		return output;
	}
	
	public void setPos(long x, long y) {
		pos_x = x;
		pos_y = y;
	}
	
	public long[] getPos() {
		long[] output = {pos_x, pos_y};
		return output;
	}
	
	public void addSpeed(double x, double y) {
		speed_x += x;
		speed_y += y;
	}
	
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.black);
		g.fillOval((int) pos_x, (int) pos_y, 20, 20);
	}
	
}
