import java.awt.Image;

import javax.swing.ImageIcon;


public class Ball {
	
	private Image ball;
	private int x;
	private int y;
	private int dx;
	private int dy;
	
	public Ball( ){ 
		ball = new ImageIcon("gfx/ball.png").getImage();
		x = 470;
		y = 220;
		dx = -10;
		dy = 10;
	}
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	
	public int getX2(){ return x + 50; }
	public int getY2(){ return y + 50; }
	
	public int getDX(){ return dx; }
	public int getDY(){ return dy; }
	
	public Image getImage(){ return ball; }
	
	public void moveX(){ x += dx; }
	public void moveY(){ y += dy; }
	
	public void changeDX(){ dx = -dx; }
	public void changeDY(){ dy = -dy; }
	
}
