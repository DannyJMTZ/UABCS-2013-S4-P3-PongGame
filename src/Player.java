import java.awt.Image;

import javax.swing.ImageIcon;


public class Player{
	
	public Image player;
	public int x;
	public int y;
	
	public Player(){
		player = new ImageIcon("gfx/player.png").getImage();
		x = 0;
		y = 100;	
	}
	
	public Image getImage(){ return player; }
	public int getX(){ return x;}
	public int getY(){ return y;}
	public int getX2(){ return x + 130;}
	public int getY2(){ return y + 130;}
	
	public void upY(){ y -= 10; }
	public void downY(){ y += 10; }
	
}
