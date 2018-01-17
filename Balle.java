import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
import java.awt.geom.AffineTransform;


public class Balle extends Thread {
    
    private int x, y;
    private Point souris;
    MaFenetreJeu frame;
    Balle(int x, int y, Point souris,  MaFenetreJeu frame){
	this.x = x;
	this.y = y;
	this.souris = souris;
	this.frame = frame;
    }
    
    @Override
    public void run(){
	BalleAnimation ba = new BalleAnimation(x,y,souris);
	ba.getImage().redimensionnerSprite(frame.getHeight(), frame.getWidth(), 283, 283, 20, 20);
	
	ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
	frame.getLayeredPane().add(ba, JLayeredPane.PALETTE_LAYER);
        animationTirer(ba);
	frame.getLayeredPane().remove(ba);
    }
    
    void animationTirer(BalleAnimation ba){
	Point i = new Point(x, y);
	
	// Changement
	// Calcul equation de droite

	if(i.getX() == souris.getX()){
	    while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0 ){
		if(i.getY()>souris.getY())
		    ba.setY(y--);
		if(i.getY()<souris.getY())
		    ba.setY(y++);
	    	i = new Point(x,y);	
		try{
		    sleep(5);
		}catch(InterruptedException e){
		    e.printStackTrace();
		}
		ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
		ba.repaint();
		frame.revalidate();
	    }
	}
	
	else if(i.getY() == souris.getY()){
	    while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0){
		if(i.getX()>souris.getX())
		    ba.setX(x--);
		if(i.getX()<souris.getX())
		    ba.setX(x++);
	    	i = new Point(x,y);	
		try{
		    sleep(5);
		}catch(InterruptedException e){
		    e.printStackTrace();
		}
		ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
		ba.repaint();
		frame.revalidate();
	    }
	}	
	else{
	    // coefficient directeur
	    double m = ((double) (souris.getY() - i.getY()) / (double) (souris.getX() - i.getX()));
	    
	    // ordonnée a l'origine
	    int p = (int) i.getY() - (int) ( m * i.getX() );

	    System.out.println("m : "+m+" & p : "+p);


	    if(i.getX()<souris.getX()){	    
		while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0){
		    ba.setX(x++);			
		    y = ((int) (m*x) )+  p;
		    
		    i = new Point(x,y);
		    try{
			sleep(0);
		    }catch(InterruptedException e){
			e.printStackTrace();
		    }
		    ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
		    ba.repaint();
		    frame.revalidate();
		}
	    }
	    
	    
	    if(i.getX()>souris.getX()){
		while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0){
		    ba.setX(x--);
		    y = ((int) (m*x) )+  p;
		    
		    i = new Point(x,y);
		    try{
			sleep(0);
		    }catch(InterruptedException e){
			e.printStackTrace();
		    }
		    ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
		    ba.repaint();
		    frame.revalidate();
		}
	    }
	}
	frame.remove(ba);
	frame.revalidate();
    }
}
    
class BalleAnimation extends JPanel {

    private int x, y;
    private Point souris;
    private Sprite image = new Sprite("./assets/balle.png");

    BalleAnimation(int x, int y, Point souris){
        this.x = x;
        this.y = y;
        this.souris = souris;
	//setOpaque(true);	
	setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	
	// Calcul Vecteur
	// Coordonnée centre image
	double Xa = 8 + x; 
	double Ya = 30 + y;

	// Coordonnée Souris
	double Xb = x;
	double Yb = y;

	double pi = 4* Math.atan(1);
		
	double degree = (Math.atan2((Yb-Ya),(Xb-Xa))+pi/2)*(180/pi);

	//g2d.rotate(Math.toRadians(degree), Xa, Ya);
	
	g2d.drawImage(image.getImage(),0,0, this);
	
    }

    void setX(int x){
	this.x = x;
    }
    
    void setY(int y){
	this.y = y;
    }

    Sprite getImage(){
	return this.image;
    }
    
}
