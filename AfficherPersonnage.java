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

class AfficherPersonnage extends JPanel {
    private Personnage steve;
    ArrayList<Enemy> personnageVisible;
    ArrayList<Boite> boiteMunition;

    public AfficherPersonnage(){
	this.personnageVisible = new ArrayList<Enemy>();
	this.boiteMunition = new ArrayList<Boite>();

    }

    void addPersonnageVisible(Enemy perso){
	this.personnageVisible.add(perso);
    }
    
    void addMunition(Boite b){
    	this.boiteMunition.add(b);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
	doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	
	// Va garder en memoire la transformation de base c'est a dire aucune
	AffineTransform originalTransform = g2d.getTransform();
	
	// Calcul Vecteur
	// Coordonnée centre image
	double Xa = 8 + steve.getCoordonneX(); 
	double Ya = 30 + steve.getCoordonneY();

	// Coordonnée Souris
	double Xb = steve.getRotationX();
	double Yb = steve.getRotationY();

	double pi = 4* Math.atan(1);
		
	double degree = (Math.atan2((Yb-Ya),(Xb-Xa))+pi/2)*(180/pi);
	
	g2d.rotate(Math.toRadians(degree), Xa, Ya);// a changer en fonction du sprite courant
	
	g2d.drawImage(steve.listeDeSprite.get(0).getImage(), steve.getCoordonneX(), steve.getCoordonneY(), this);
	g2d.setTransform(originalTransform); // Reinitialise la transformation comme sauvegarder ulterierement

	//HITBOX
	g.setColor(Color.BLUE);
	g.drawRect((int)steve.getHitbox().getX(), (int)steve.getHitbox().getY(), steve.getHitbox().getWidth(), steve.getHitbox().getHeight());

	
	if(!personnageVisible.isEmpty()){
	    for(int i = 0 ; i < personnageVisible.size() ; i++){
		Enemy currentEnemy = personnageVisible.get(i);
		if(currentEnemy.getVie() <= 0){
		    currentEnemy.setHitbox(new Hitbox());
		    int val = -1;
		    for(int tmp=0 ; tmp < boiteMunition.size() ; tmp++) {
		    	if(i == boiteMunition.get(tmp).getId()) {
			    val = tmp;
		    	}
		    }
		    if(val != -1) {
			if(boiteMunition.get(val).getAfficher() == 0) {
			    g2d.drawImage(boiteMunition.get(val).getImage(),boiteMunition.get(val).getCoordonneX(),boiteMunition.get(val).getCoordonneY() , this);
			    boiteMunition.get(val).setAfficher(1);
			}else if(boiteMunition.get(val).getAfficher() == 1) {
			    g2d.drawImage(boiteMunition.get(val).getImage(),boiteMunition.get(val).getCoordonneX(),boiteMunition.get(val).getCoordonneY() , this);
			}
		    }
		    g2d.drawImage(currentEnemy.listeDeSprite.get(1).getImage(), currentEnemy.getCoordonneX(), currentEnemy.getCoordonneY(), this);
		}
		else
		    g2d.drawImage(currentEnemy.listeDeSprite.get(0).getImage(), currentEnemy.getCoordonneX(), currentEnemy.getCoordonneY(), this);      
                g.drawRect((int)currentEnemy.getHitbox().getX(), (int)currentEnemy.getHitbox().getY(), currentEnemy.getHitbox().getWidth(), currentEnemy.getHitbox().getHeight());
	    }
	}
	
    }
    
    public void setSteve(Personnage steve){
	this.steve = steve;
    }

    public Personnage getSteve(){
	return this.steve;
    }
}
