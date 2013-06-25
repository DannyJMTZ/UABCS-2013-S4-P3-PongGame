import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Pong extends JFrame implements KeyListener, Runnable {
	
	private Ball ball;
	private Player player;
	
	public Pong(){
		
		this.setLayout( null );
		this.setDefaultCloseOperation( this.EXIT_ON_CLOSE );
		this.setTitle( "Pong" );
		this.setSize( 1000 , 500 );
		this.setLocationRelativeTo( null );
		this.setIconImage( Toolkit.getDefaultToolkit().getImage( "gfx/ball.png" ) );
		this.getContentPane().setBackground( new Color( 0 , 130 , 0 ) );
		
		ball = new Ball();
		player = new Player();
		Thread ballMovement = new Thread( this );
		ballMovement.start();
		this.addKeyListener( this );
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		// pintar la portería
		g2.setColor( Color.DARK_GRAY );
		
		g2.fill( new Rectangle2D.Double( this.getBounds().width - 50 , 0 , 50 , this.getBounds().height / 5 ));
		g2.fill( new Rectangle2D.Double( this.getBounds().width - 50 , 4 * (this.getBounds().height / 5) , 50 , (this.getBounds().height / 5) ));
		g2.fill( new Rectangle2D.Double( this.getBounds().width - 25 , 0 , 25, this.getBounds().height ));
		
		g2.setColor( Color.LIGHT_GRAY );
		
		// pintar lo de enmedio
		g2.fill( new Rectangle2D.Double( (this.getBounds().width / 2 ) - 5 , 0 , 10, this.getBounds().height ));
		g2.drawOval( 2 *(this.getBounds().width / 5) , 2 * (this.getBounds().height / 6) ,  this.getBounds().width / 5 , 2 * (this.getBounds().height / 6) );
		
		g2.drawImage( ball.getImage(), ball.getX() , ball.getY(), null );
		g2.drawImage( player.getImage(), player.getX() , player.getY(), null );
		
		/*
		// COLISION CON LAS PAREDES
		if( this.ball.getDX() > 0 && this.ball.getX2() >= this.getBounds().width )
			ball.changeDX();
		else if( this.ball.getDX() < 0 && this.ball.getX() <= 0 )
			ball.changeDX();
		
		if( this.ball.getDY() > 0 && this.ball.getY2() >= this.getBounds().height )
			ball.changeDY();
		else if( this.ball.getDY() < 0 && this.ball.getY() <= 0 )
			ball.changeDY();
		
		// COLISION CON EL JUGADOR
		if( this.ball.getX() == this.player.getX2() && 
			( ( this.ball.getY() >= this.player.getY() && this.ball.getY() <= this.player.getY2() ) ||
			( this.ball.getY2() >= this.player.getY() && this.ball.getY2() <= this.player.getY2() ) ) )
			ball.changeDX();
		
		if( this.ball.getY2() == this.player.getY() && 
			this.ball.getX() >= this.player.getX() && this.ball.getX() <= this.player.getX2())
			ball.changeDY();
		else if( this.ball.getY() == this.player.getY2() && 
			this.ball.getX() >= this.player.getX() && this.ball.getX() <= this.player.getX2())
			ball.changeDY();
		*/
		
		if( this.ball.getDX() > 0 && this.ball.getX2() >= this.getBounds().width )
			ball.changeDX();
		else if( this.ball.getDX() < 0 && this.ball.getX() <= 0 )
			ball.changeDX();
		else if( this.ball.getX() == this.player.getX2() && 
			( ( this.ball.getY() >= this.player.getY() && this.ball.getY() <= this.player.getY2() ) ||
			( this.ball.getY2() >= this.player.getY() && this.ball.getY2() <= this.player.getY2() ) ) )
			ball.changeDX();
		
		if( this.ball.getDY() > 0 && this.ball.getY2() >= this.getBounds().height )
			ball.changeDY();
		else if( this.ball.getDY() < 0 && this.ball.getY() <= 0 )
			ball.changeDY();
		else if( this.ball.getY2() == this.player.getY() && 
			this.ball.getX() >= this.player.getX() && this.ball.getX() <= this.player.getX2())
			ball.changeDY();
		else if( this.ball.getY() == this.player.getY2() && 
			this.ball.getX() >= this.player.getX() && this.ball.getX() <= this.player.getX2())
			ball.changeDY();
		
		g.dispose();
		g2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch( e.getExtendedKeyCode() ){
		
		case 38:	
			if( player.getY() - 10 >= 0 )
				player.upY();
			break;
		case 40:
			if( player.getY2() + 10 <= this.getBounds().getHeight() )
				player.downY();
			break;
		}		
	}

	@Override
	public void keyReleased(KeyEvent arg0) { }

	@Override
	public void keyTyped(KeyEvent arg0) { }

	@Override
	public void run() {
		for(;;){
			try {
				Thread.sleep( 40 );
				this.repaint();
				this.ball.moveX();
				this.ball.moveY();
			}
			catch (InterruptedException e) { }
		}
	}

	public static void main(String[] args) {
		
		Pong pong = new Pong();
		pong.setVisible( true );
		
	}

}
