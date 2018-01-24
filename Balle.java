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
    private AfficherPersonnage af;
    MaFenetreJeu frame;
    BalleAnimation ba;
    private Personnage perso;
    
    Balle(Personnage perso, Point souris,  MaFenetreJeu frame, AfficherPersonnage af){
	this.perso = perso;
	//Penser a enlver x et y des attribut car seul les x et y de balle animation sont utile
	this.x = perso.getCoordonneX();
	this.y = perso.getCoordonneY();
	this.souris = souris;
	this.frame = frame;
	this.af=af;
    }
    
    @Override
    public void run(){
	BalleAnimation ba = new BalleAnimation(x,y,souris, af, frame, this);
	ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
	frame.getLayeredPane().add(ba, JLayeredPane.MODAL_LAYER);
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
		//frame.revalidate();
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
		//frame.revalidate();
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
		    ba.setY(y);
		    
		    i = new Point(x,y);
		    try{
			sleep(5);
		    }catch(InterruptedException e){
			e.printStackTrace();
		    }
		    ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
		    ba.repaint();
		    //  frame.revalidate();
		}
	    }
	    
	    
	    if(i.getX()>souris.getX()){
		while(x < frame.getWidth() && y < frame.getHeight() && x > 0 && y > 0){
		    ba.setX(x--);
		    y = ((int) (m*x) )+  p;
		    ba.setY(y);
		    
		    i = new Point(x,y);
		    try{
			sleep(5);
		    }catch(InterruptedException e){
			e.printStackTrace();
		    }
		    ba.setBounds(x, y, ba.getImage().getImageIcon().getIconWidth(), ba.getImage().getImageIcon().getIconHeight());
		    ba.repaint();
		    //frame.revalidate();
		}
	    }
	}
	frame.getLayeredPane().remove(ba);
	//frame.revalidate();
	this.stop2();
    }

    @SuppressWarnings(value={"unchecked", "deprecation"})
    void stop2(){
	try{
	    this.stop();
	}catch(Exception e){
	    System.out.println("Erreur : mauvaise fermeture du Thread balle");
	}
    }
    
    Personnage getPerso(){
	return this.perso;
    }
}

class BalleAnimation extends JPanel {
    
    private int x, y;
    private Point souris;
    private Sprite image = new Sprite("./assets/balle.png");
    private Hitbox balleHB;
    private AfficherPersonnage af;
    private Balle balle;
    private MaFenetreJeu frame;
    
    BalleAnimation(int x, int y, Point souris,  AfficherPersonnage af, MaFenetreJeu frame, Balle balle){
        this.x = x;
        this.y = y;
        this.souris = souris;
	this.af = af;
	this.image.redimensionnerSprite(frame.getHeight(), frame.getWidth(), 283, 283, 20, 20);
	this.balleHB = new Hitbox(this);
	this.balle=balle;
	this.frame = frame;
	//setOpaque(true);	
	setBackground(new Color(0,0,0,0));
    }
    
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	for(int i = 0 ; i!=af.personnageVisible.size();i++){
            if(Hitbox.collision(balleHB, af.personnageVisible.get(i).getHitbox()) && balle.getPerso()!=af.personnageVisible.get(i)){
		//af.personnageVisible.get(i).setHitbox(new Hitbox());  Cette ligne a été deplacer dans AfficherPersonnage car sinon les sprites de mort ne s'affichait plus
		af.personnageVisible.get(i).setVie(af.personnageVisible.get(i).getVie()-1);
		frame.getLayeredPane().remove(this);
		balle.stop2();
	    }
    	}
	
	/*
	// Calcul Vecteur
	// Coordonnée centre image
	double Xa = 8 + x; 
	double Ya = 30 + y;
	
	// Coordonnée Souris
	double Xb = x;
	double Yb = y;
	
	double pi = 4* Math.atan(1);
		
	double degree = (Math.atan2((Yb-Ya),(Xb-Xa))+pi/2)*(180/pi);

	g2d.rotate(Math.toRadians(degree), Xa, Ya);
	*/	
	g2d.drawImage(image.getImage(),0,0, this);
	
    }

    int getCoordonneX(){
	return this.x;
    }
    
    void setX(int x){
	this.x = x;
	this.balleHB.setX(x);
    }

    
    int getCoordonneY(){
	return this.y;
    }
    
    void setY(int y){
	this.y = y;
	this.balleHB.setY(y);
    }

    Sprite getImage(){
	return this.image;
    }
    
}
