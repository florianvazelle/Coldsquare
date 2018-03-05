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

    Jeu j;
    MenuPause mp;
    
    public AfficherPersonnage(Jeu j, MenuPause mp){
	this.personnageVisible = new ArrayList<Enemy>();
	this.boiteMunition = new ArrayList<Boite>();

	this.j = j;
	this.mp = mp;
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

	
	if(steve.getVie()<=0){
	    g2d.drawImage(steve.listeDeSprite.get(1).getImage(), steve.getCoordonneX(), steve.getCoordonneY(), this);
	    MenuMort mm = new MenuMort(this.j);
            mp.setEnPause(true);
	}
	else{
	    double degree = orientation(8 + steve.getCoordonneX(), 30 + steve.getCoordonneY(), steve.getRotationX(), steve.getRotationY());
	    g2d.rotate(Math.toRadians(degree), 8 + steve.getCoordonneX(), 30 + steve.getCoordonneY());
	    g2d.drawImage(steve.listeDeSprite.get(0).getImage(), steve.getCoordonneX(), steve.getCoordonneY(), this);
	}
	g2d.setTransform(originalTransform); // Reinitialise la transformation comme sauvegarder ulterierement
	
	//HITBOX
	/*
	  g.setColor(Color.BLUE);
	  g.drawRect((int)steve.getHitbox().getX(), (int)steve.getHitbox().getY(), steve.getHitbox().getWidth(), steve.getHitbox().getHeight());
	*/
	
	if(!personnageVisible.isEmpty()){
	    for(int i = 0 ; i < personnageVisible.size() ; i++){
		Enemy currentEnemy = personnageVisible.get(i);
		if(currentEnemy.getVie() <= 0){
		    currentEnemy.setHitbox(new Hitbox());
		    int val = -1;
		    for(int tmp=0 ; tmp < boiteMunition.size() ; tmp++) {
			if(i+1 == boiteMunition.get(tmp).getId()) {
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
		else {
		    if(currentEnemy.getAwake()){
			double degreeEnemy = orientation(8+currentEnemy.getCoordonneX(), 15+currentEnemy.getCoordonneY(), steve.getCoordonneX(), steve.getCoordonneY());
		    	g2d.rotate(Math.toRadians(degreeEnemy), 8+currentEnemy.getCoordonneX(), 15+currentEnemy.getCoordonneY());
		    }
		    g2d.drawImage(currentEnemy.listeDeSprite.get(0).getImage(), currentEnemy.getCoordonneX(), currentEnemy.getCoordonneY(), this);
		    g2d.setTransform(originalTransform); // Reinitialise la transformation comme sauvegarder ulterierement
		}
		//HITBOX
		//g.drawRect((int)currentEnemy.getHitbox().getX(), (int)currentEnemy.getHitbox().getY(), currentEnemy.getHitbox().getWidth(), currentEnemy.getHitbox().getHeight());
	    }
	}
	
    }

    private double orientation(double Xa, double Ya, double Xb, double Yb){
	// Calcul Vecteur
	double pi = 4* Math.atan(1);
	double degree = (Math.atan2((Yb-Ya),(Xb-Xa))+pi/2)*(180/pi);
	return degree;
    }
	
    public void setSteve(Personnage steve){
	this.steve = steve;
    }
    
    public Personnage getSteve(){
	return this.steve;
    }
}
