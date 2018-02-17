package Force_package;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame screen = new JFrame();
		
		graphics Graphics = new graphics();
		
		screen.setBounds(0,0,700,650);
		screen.setTitle("Force");
		screen.setResizable(false);
		screen.setVisible(true);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		screen.add(Graphics);

	}

}
