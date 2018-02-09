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
    ArrayList<Personnage> personnageVisible;
    ArrayList<Boite> boiteMunition;

    public AfficherPersonnage(){
	this.personnageVisible = new ArrayList<Personnage>();
	this.boiteMunition = new ArrayList<Boite>();

    }

    void addPersonnageVisible(Personnage perso){
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
	
	//Changement
	int i = 0;

	// Va garder en memoire la transformation de base c'est a dire aucune
	AffineTransform originalTransform = g2d.getTransform();
	
	// Calcul Vecteur
	// Coordonnée centre image
	double Xa = 8 /* personnageVisible.get(i).listeDeSprite.get(0).getCentre().getX() */ +personnageVisible.get(i).getCoordonneX(); 
	double Ya = 30 /*personnageVisible.get(i).listeDeSprite.get(0).getCentre().getY()*/+personnageVisible.get(i).getCoordonneY();

	// Coordonnée Souris
	double Xb = personnageVisible.get(i).getRotationX();
	double Yb = personnageVisible.get(i).getRotationY();

	double pi = 4* Math.atan(1);
		
	double degree = (Math.atan2((Yb-Ya),(Xb-Xa))+pi/2)*(180/pi);

	g2d.rotate(Math.toRadians(degree), Xa, Ya);// a changer en fonction du sprite courant
		
	if(!personnageVisible.isEmpty()){
	    for(i=0;i< personnageVisible.size();i++){
		if(personnageVisible.get(i).getVie()<=0){
		    personnageVisible.get(i).setHitbox(new Hitbox());
		    int val=0;
		    for(int tmp=0;tmp<boiteMunition.size();tmp++) {
		    	if(i==boiteMunition.get(tmp).getId()) {
		    		val=tmp;
		    	}
		    }
		    
		    if(boiteMunition.get(val).getAfficher()== 0) {
				   System.out.println("BOITE MUNITION:0"+boiteMunition.get(val).getValue());
				   g2d.drawImage(boiteMunition.get(val).getImage(),boiteMunition.get(val).getCoordonneX(),boiteMunition.get(val).getCoordonneY() , this);
				   boiteMunition.get(val).setAfficher(1);
			}else if(boiteMunition.get(val).getAfficher()== 1) {
				   g2d.drawImage(boiteMunition.get(val).getImage(),boiteMunition.get(val).getCoordonneX(),boiteMunition.get(val).getCoordonneY() , this);
			}
		    
		    g2d.drawImage(personnageVisible.get(i).listeDeSprite.get(1).getImage(), personnageVisible.get(i).getCoordonneX(), personnageVisible.get(i).getCoordonneY(), this);
		}
		else
		    g2d.drawImage(personnageVisible.get(i).listeDeSprite.get(0).getImage(), personnageVisible.get(i).getCoordonneX(), personnageVisible.get(i).getCoordonneY(), this);      
		g2d.setTransform(originalTransform); // Reinitialise la transformation comme sauvegarder ulterierement
	    }
	}
    }
    
    // Je pense qu'il faudra généraliser la boucle for(i=0;i< personnageVisible.size();i++){ car par la suite il faudra calculer la rotation des ennemie
    
}
